import nprogress from "nprogress"

// 显示页面加载 Loading
export function showPageLoading() {
    nprogress.start()
}

// 隐藏页面加载 Loading
export function hidePageLoading() {
    nprogress.done()
}

// 弹出消息提示框
let messageTimer = null;

export function showMessage(message = '提示内容', type = 'success', customClass = '') {
    // 如果定时器存在，清除定时器
    if (messageTimer) {
        clearTimeout(messageTimer);
    }

    // 设置一个新的定时器
    messageTimer = setTimeout(() => {
        ElMessage({
            type: type,
            message,
            customClass,
        });
        // 清除定时器
        messageTimer = null;
    }, 300); // 防抖时间设置为300毫秒
    return true;
}
// 弹出确认框
export function showModel(content = '提示内容', type = 'warning', title = '') {
    return ElMessageBox.confirm(
        content,
        title, {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type,
        }
    )
}