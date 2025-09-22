<template>
    <div class="grid grid-cols-2 h-screen">
        <!-- 默认占两列，order 用于指定排列顺序，md 用于适配非移动端（PC 端） -->
        <div class="col-span-2 order-2 p-10 md:col-span-1 md:order-1 bg-slate-900">
            <!-- 指定为 flex 布局，并设置为屏幕垂直水平居中，高度为 100% -->
            <div
                class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInLeft animate__fast">
                <h2 class="font-bold text-4xl mb-7 text-white">东隅已逝，桑榆非晚</h2>
                <p class="text-white">DissCheng开发的前后端分离博客。</p>
                <!-- 指定图片宽度为父级元素的 1/2 -->
                <img src="@/assets/developer.png" class="w-1/2">
            </div>
        </div>
        <div class="col-span-2 order-1 md:col-span-1 md:order-2 bg-white">
            <!-- flex-col 用于指定子元素垂直排列 -->
            <div
                class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInRight animate__fast">
                <!-- 大标题，设置字体粗细、大小、下边距 -->
                <h1 class="font-bold text-4xl mb-5">欢迎回来</h1>
                <!-- 设置 flex 布局，内容垂直水平居中，文字颜色，以及子内容水平方向 x 轴间距 -->
                <div class="flex items-center justify-center mb-7 text-gray-400 space-x-2">
                    <!-- 左边横线，高度为 1px, 宽度为 16，背景色设置 -->
                    <span class="h-[1px] w-16 bg-gray-200"></span>
                    <span>账号密码登录</span>
                    <!-- 右边横线 -->
                    <span class="h-[1px] w-16 bg-gray-200"></span>
                </div>
                <!-- 引入 Element Plus 表单组件，移动端设置宽度为 5/6，PC 端设置为 2/5 -->
                <el-form class="w-5/6 md:w-2/5" ref="formRef" :rules="rules" :model="form">
                    <el-form-item prop="username">
                        <!-- 输入框组件 -->
                        <el-input size="large" v-model="form.username" placeholder="请输入用户名" :prefix-icon="User"
                            clearable />
                    </el-form-item>
                    <el-form-item prop="password">
                        <!-- 密码框组件 -->
                        <el-input size="large" type="password" v-model="form.password" placeholder="请输入密码"
                            :prefix-icon="Lock" clearable show-password />
                    </el-form-item>
                    <el-form-item>
                        <!-- 登录按钮，宽度设置为 100% -->
                        <el-button class="w-full mt-2" size="large" :loading="loading" type="primary"
                            @click="onSubmitLogin" v-if="onVisible === 'login'">登录</el-button>
                        <!-- 注册按钮，宽度设置为 100% -->
                        <el-button class="w-full mt-2" size="large" :loading="loading" type="primary"
                            @click="onSubmitRegister" v-if="onVisible === 'register'">注册</el-button>
                        <!-- 注册-->
                        <el-button class="w-full mt-2" size="large" type="text" @click="onVisible = 'register'" v-if="
                            onVisible === 'login'">没有账号？去注册</el-button>
                        <!-- 注册-->
                        <el-button class="w-full mt-2" size="large" type="text" @click="onVisible = 'login'"
                            v-if="onVisible === 'register'">已有账号？去登录</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script setup>
// 引入 Element Plus 中的用户、锁图标
import { User, Lock } from '@element-plus/icons-vue'
import { login, register } from "@/api/admin/user.js";
import { setToken, getToken, setRefreshToken, getRefreshToken } from '@/composables/cookie'
import { showMessage } from "@/composables/util"
import { ref, reactive, onMounted, onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { time } from 'echarts';
import { set } from 'nprogress';
const userStore = useUserStore()
// 定义响应式的表单对象
const form = reactive({
    username: '',
    password: ''
})
const router = useRouter()
// 表单引用
const formRef = ref(null)
// 表单验证规则
const rules = {
    username: [
        {
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: '密码不能为空',
            trigger: 'blur',
        },
    ]
}
const onVisible = ref('login')
// 登录按钮加载
const loading = ref(false)
// 登录
const onSubmitLogin = async () => {
    const valid = await formRef.value.validate();
    if (!valid) {
        return false;
    }

    loading.value = true;
    try {
        const res = await login(form.username, form.password);
        if (res.code === 1) {
            const token = res.data.jwtToken;
            const refreshToken = res.data.refreshToken;
            setToken(token);
            setRefreshToken(refreshToken);
            await userStore.setUserInfo(); // 确保 setUserInfo 是一个异步方法
            showMessage('登录成功', 'success');
            router.push('/')
        } else {
            showMessage('用户名或密码错误', 'error');
        }
    } catch (error) {
        showMessage('登录失败，请稍后再试', 'error');
    } finally {
        loading.value = false;
    }
};
// 注册
const onSubmitRegister = () => {
    formRef.value.validate((valid) => {
        if (!valid) {
            return false
        }
        loading.value = true;
        register(form.username, form.password).then((res) => {
            if (res.code === 1) {
                let token = res.data.jwtToken;
                setToken(token)
                setRefreshToken(res.data.refreshToken)
                // 获取用户信息，并存储到全局状态中
                userStore.setUserInfo()

                showMessage('注册成功', 'success')
                router.push('/')
            } else {
                showMessage('注册失败', 'error')
            }
        })
    }).finally(() => {
        loading.value = false;
    });
}

// 按回车键后，执行
function onKeyUp(e) {
    if (e.key == 'Enter') {
        if (onVisible.value === 'login') {
            onSubmitLogin()
        } else {
            onSubmitRegister()
        }
    }

}

// 添加键盘监听
onMounted(() => {
    document.addEventListener('keyup', onKeyUp)
})

// 移除键盘监听
onBeforeUnmount(() => {
    document.removeEventListener('keyup', onKeyUp)
})
</script>