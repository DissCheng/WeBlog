<template>
  <div class="mt-14">
    <h2 class="flex justify-center items-center mb-7 text-gray-500">全部评论<span>({{ total }})</span></h2>
    <!-- 卡片 -->
    <div
        class="w-full px-5 py-10 mb-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
      <!-- 评论发布表单 -->
      <form>
        <div
            class="w-full mb-4 border border-gray-200 rounded-lg bg-gray-50 dark:bg-gray-700 dark:border-gray-600">
          <div class="px-4 py-2 bg-white rounded-t-lg dark:bg-gray-800">
            <label for="comment" class="sr-only">Your comment</label>
            <textarea id="comment" rows="4"
                      class="w-full px-0 text-sm text-gray-900 bg-white border-0 dark:bg-gray-800 focus:ring-0 dark:text-white dark:placeholder-gray-400"
                      :placeholder="replyArticlePlaceholderText" required
                      v-model="articleCommentContext"></textarea>
          </div>
          <div class="flex items-center justify-between px-3 py-2 border-t dark:border-gray-600">
            <div class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white
bg-sky-600 rounded-lg focus:ring-4 focus:ring-sky-200 dark:focus:ring-sky-900 hover:bg-sky-700"
            @click="submitCommentForm(-1,-1)">
              发送
            </div>
            <div class="flex ps-0 space-x-1 rtl:space-x-reverse sm:ps-2">
              <div type="button"
                   class="inline-flex justify-center items-center p-2 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600">
                <svg class="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                     viewBox="0 0 24 24">
                  <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                        stroke-width="2"
                        d="M15 9h0M9 9h0m12 3a9 9 0 1 1-18 0 9 9 0 0 1 18 0ZM7 13c0 1 .5 2.4 1.5 3.2a5.5 5.5 0 0 0 7 0c1-.8 1.5-2.2 1.5-3.2 0 0-2 1-5 1s-5-1-5-1Z"/>
                </svg>
              </div>
            </div>
          </div>
        </div>
      </form>
      <!-- 评论列表 -->
      <div v-if="comments && comments.length > 0" v-for="(comment, index) in comments" :key="index">
        <!-- 一级评论 -->
        <div class="flex gap-3 mt-5">
          <!-- 左边头像栏 -->
          <div>
            <img v-if="comment.avatar && comment.avatar.length > 0" :src="comment.avatar"
                 class="w-10 h-10 rounded-full">
            <svg v-else class="w-10 h-10 text-gray-400 rounded-full dark:text-gray-400" aria-hidden="true"
                 xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
              <path
                  d="M10 0a10 10 0 1 0 10 10A10.011 10.011 0 0 0 10 0Zm0 5a3 3 0 1 1 0 6 3 3 0 0 1 0-6Zm0 13a8.949 8.949 0 0 1-4.951-1.488A3.987 3.987 0 0 1 9 13h2a3.987 3.987 0 0 1 3.951 3.512A8.949 8.949 0 0 1 10 18Z"/>
            </svg>
          </div>
          <!-- 右边评论信息 -->
          <div class="flex flex-col gap-2 grow">
            <!-- 昵称 -->
            <div class="text-xs text-[#FB7299] font-bold">{{ comment.nickname }}</div>
            <!-- 评论内容 -->
            <div class="text-sm dark:text-gray-400">{{ comment.content }}</div>
            <!-- Meta 信息 -->
            <div class="flex items-center text-xs text-gray-400">
              <!-- 发布时间 -->
              <div>{{ comment.createTime }}</div>
              <!-- 一级评论回复 -->
              <div class="text-gray-400 cursor-pointer ml-4 hover:text-sky-600"
                   @click="showReplyForm(index,-1, comment.nickname, comment.id, comment.id)">
                回复
              </div>
              <div class="text-gray-400 cursor-pointer ml-4 hover:text-sky-600"
                   @click="">
                删除
              </div>
            </div>
            <div v-if="comment.replies > 0"
                 class="text-xs text-gray-400 cursor-pointer ml-4 hover:text-sky-600">
              <!-- 二级评论回复 -->
              <div class="text-gray-400 cursor-pointer ml-4 hover:text-sky-600"
                   @click="showChildReply(index)">
                {{ comment.expanded ? '收起回复' : `展开全部${comment.replies}条回复` }}
              </div>
            </div>
          </div>
        </div>
        <!-- 一级评论回复表单 -->
        <form v-if="comment.isShowReplyForm">
          <div
              class="w-full mb-4 border border-gray-200 rounded-lg bg-gray-50 dark:bg-gray-700 dark:border-gray-600">
            <div class="px-4 py-2 bg-white rounded-t-lg dark:bg-gray-800">
              <label for="comment" class="sr-only">Your comment</label>
              <textarea id="comment" rows="4"
                        class="w-full px-0 text-sm text-gray-900 bg-white border-0 dark:bg-gray-800 focus:ring-0 dark:text-white dark:placeholder-gray-400"
                        :placeholder="replyPlaceholderText" required
                        v-model="commentContext"></textarea>
            </div>
            <div class="flex items-center justify-between px-3 py-2 border-t dark:border-gray-600">
              <div class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white
bg-sky-600 rounded-lg focus:ring-4 focus:ring-sky-200 dark:focus:ring-sky-900 hover:bg-sky-700"
                   @click="submitCommentForm(index,-1)">
                发送
              </div>
              <div class="flex ps-0 space-x-1 rtl:space-x-reverse sm:ps-2">
                <div type="button"
                     class="inline-flex justify-center items-center p-2 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600">
                  <svg class="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                       viewBox="0 0 24 24">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                          stroke-width="2"
                          d="M15 9h0M9 9h0m12 3a9 9 0 1 1-18 0 9 9 0 0 1 18 0ZM7 13c0 1 .5 2.4 1.5 3.2a5.5 5.5 0 0 0 7 0c1-.8 1.5-2.2 1.5-3.2 0 0-2 1-5 1s-5-1-5-1Z"/>
                  </svg>
                </div>
              </div>
            </div>
          </div>
        </form>
        <!-- 二级评论 -->
        <!-- Meta 信息 -->
        <div class="ml-12" v-if="comment.expanded">
          <div v-for="(childComment, index2) in comment.childComments" :key="index2">
            <!-- 头像、昵称、评论内容 -->
            <div class="flex items-center gap-3 mt-5">
              <!-- 左边头像栏 -->
              <div>
                <img v-if="childComment.avatar && childComment.avatar.length > 0"
                     :src="childComment.avatar" class="w-6 h-6 rounded-full">
                <svg v-else class="w-6 h-6 text-gray-400 rounded-full dark:text-gray-400"
                     aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor"
                     viewBox="0 0 20 20">
                  <path
                      d="M10 0a10 10 0 1 0 10 10A10.011 10.011 0 0 0 10 0Zm0 5a3 3 0 1 1 0 6 3 3 0 0 1 0-6Zm0 13a8.949 8.949 0 0 1-4.951-1.488A3.987 3.987 0 0 1 9 13h2a3.987 3.987 0 0 1 3.951 3.512A8.949 8.949 0 0 1 10 18Z"/>
                </svg>
              </div>
              <!-- 昵称 -->
              <!-- 昵称 -->
              <div class="text-xs text-[#FB7299] font-bold">
                {{ childComment.nickname }}
                <!-- 【回复 @xxx】 -->
                <span v-if="childComment.replyNickname" class="text-gray-400 font-normal ml-1 mr-1">回复
                                    <span class="text-sky-600 font-normal text-sm">@{{ childComment.replyNickname }}</span>
                                    <span class="text-gray-400"> :</span>
                                </span>
              </div>
              <!-- 评论内容 -->
              <div class="text-sm dark:text-gray-400">{{ childComment.content }}</div>
            </div>
            <!-- Meta 信息 -->
            <div class="ml-9 mt-1 flex items-center text-xs text-gray-400">
              <!-- 发布时间 -->
              <div>{{ childComment.createTime }}</div>
              <!-- 二级评论回复 -->
              <div class="text-gray-400 cursor-pointer ml-4 hover:text-sky-600"
                   @click="showReplyForm(index, index2, childComment.nickname, childComment.id, comment.id)">
                回复
              </div>
              <div class="text-gray-400 cursor-pointer ml-4 hover:text-sky-600"
                   @click="">
                删除
              </div>
            </div>
            <!-- 二级评论回复表单 -->
            <form v-if="childComment.isShowReplyForm">
              <div
                  class="w-full mb-4 border border-gray-200 rounded-lg bg-gray-50 dark:bg-gray-700 dark:border-gray-600">
                <div class="px-4 py-2 bg-white rounded-t-lg dark:bg-gray-800">
                  <label for="comment" class="sr-only">Your comment</label>
                  <textarea id="comment" rows="4"
                            class="w-full px-0 text-sm text-gray-900 bg-white border-0 dark:bg-gray-800 focus:ring-0 dark:text-white dark:placeholder-gray-400"
                            :placeholder="replyPlaceholderText" required
                            v-model="commentContext"></textarea>
                </div>
                <div class="flex items-center justify-between px-3 py-2 border-t dark:border-gray-600">
                  <div class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white
bg-sky-600 rounded-lg focus:ring-4 focus:ring-sky-200 dark:focus:ring-sky-900 hover:bg-sky-700"
                  @click="submitCommentForm(index,index2)">
                    发送
                  </div>
                  <div class="flex ps-0 space-x-1 rtl:space-x-reverse sm:ps-2">
                    <div type="button"
                         class="inline-flex justify-center items-center p-2 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600">
                      <svg class="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                           viewBox="0 0 24 24">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                              stroke-width="2"
                              d="M15 9h0M9 9h0m12 3a9 9 0 1 1-18 0 9 9 0 0 1 18 0ZM7 13c0 1 .5 2.4 1.5 3.2a5.5 5.5 0 0 0 7 0c1-.8 1.5-2.2 1.5-3.2 0 0-2 1-5 1s-5-1-5-1Z"/>
                      </svg>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <!-- 加载更多 -->
      <div v-if="loading" class="loading">加载中...</div>
      <div
          v-else-if="hasMore"
          class="load-more"
          @click="loadMore"
      >
        点击加载更多
      </div>
      <div v-else class="no-more">没有更多评论了</div>
    </div>
  </div>
</template>


<script setup>
import {ref, computed, watch, reactive, onMounted, nextTick} from 'vue'
import {initPopovers, initTooltips} from "flowbite";
import {getComment,addComment} from "@/api/frontend/comment.js";
import {useRoute, useRouter} from "vue-router";
import {showMessage} from "@/composables/util.js";

let total = ref(0)
//当前路由
const route = useRoute()
//评论内容
const commentContext = ref('')
const articleCommentContext = ref('')
// 评论数组
const rawComments = ref([])
const rawSonComments = ref([])
// 带外挂的评论数组（响应式）
const childComments = computed(() =>
    rawSonComments.value.map(item => (reactive({
      ...item,               // 后端字段
      nickname: "用户"+item.authorId,
      replyNickname: "用户"+item.toAuthorId,
      childComments: [],
      childCommentsCnt: 0,
      expanded: false,
      isShowReplyForm: false
    })))
)
const comments = computed(() =>
    rawComments.value.map(item => (reactive({
      ...item,               // 后端字段
      nickname: "用户"+item.authorId,
      replyNickname: null,
      childComments: childComments,
      childCommentsCnt: 0,
      expanded: false,
      isShowReplyForm: false
    })))
)

onMounted(()=> {
      refreshComment()
    }
)

watch(()=>route.params.articleId, (value, oldValue, onCleanup)=>refreshComment())

//获取文章一级评论
function refreshComment() {
  rawComments.value=[]
  getComment({
    articleId: route.params.articleId,
    isPrimary: true,
    pageSize: 10,
    pageNum: 1
  }).then((res) => {
    rawComments.value = res.data.comment
  })

}

//获取一级评论的二级评论
function refreshSonComment(index) {
  rawSonComments.value = []
  getComment({
    articleId: route.params.articleId,
    rootId: comments.value[index].id,
    isPrimary: false,
    pageSize: 10,
    pageNum: 1
  }).then((res) => {
    rawSonComments.value = res.data.comment
    total = comments.value.length
  })
}

//提交评论
function submitCommentForm(index1, index2) {
  let replyId = null
  let rootId = null
  let isPrimary = false
  let context = ''
  let toAuthorId = null

  if (index1 === -1) {
    isPrimary = true;
    context = articleCommentContext.value
    addComment({
      "articleId": route.params.articleId,
      "replyId": replyId,
      "rootId": rootId,
      "isPrimary": isPrimary,
      "content": context
    }).then((res) => {
      if(res.code!==200){
        showMessage(res.data.message)
      }else{
        showMessage("发送成功",'success')
      }
      commentContext.value=''
      articleCommentContext.value=''
      refreshComment()
    })
  } else {
    showReplyForm(index1,index2,"")
    if (index2 === -1) {
      replyId = comments.value[index1].id
      toAuthorId = comments.value[index1].authorId
    }else{
      replyId = comments.value[index1].childComments[index2].id
      toAuthorId = comments.value[index1].childComments[index2].authorId
    }

    rootId = comments.value[index1].id;
    context = commentContext.value
    isPrimary = false
    addComment({
      "articleId": route.params.articleId,
      "toAuthorId": toAuthorId,
      "replyId": replyId,
      "rootId": rootId,
      "isPrimary": isPrimary,
      "content": context
    }).then((res) => {
      if(res.code!==200){
        showMessage(res.data.message)
      }else{
        showMessage("发送成功",'success')
      }
      commentContext.value=''
      articleCommentContext.value=''
      refreshSonComment(index1)
      comments.value[index1].replies++;
    })
  }
}

// 回复 textarea 的 placeholder 提示文字
const replyArticlePlaceholderText = ref('发表一个友善的评论吧...')
const replyPlaceholderText = ref('发表一个友善的评论吧...')
// 展示回复表单
const showReplyForm = (index1, index2, nickname) => {
  // 先将评论数组中一级评论的所有 isShowReplyForm 字段设置为 false
  let beforeComment = 0
  if (index2 === -1) {
    //一级评论
    beforeComment = comments.value[index1].isShowReplyForm
  } else {
    //二级评论
    beforeComment = comments.value[index1].childComments[index2].isShowReplyForm
  }
  comments.value.forEach(c => {
    c.isShowReplyForm = false
    c.childComments.forEach(child => {
      child.isShowReplyForm = false
    })
  })
  if (index2 === -1) {
    // 拿到当前下标的评论
    let afterComment = comments.value[index1]
    afterComment.isShowReplyForm = !beforeComment
  } else {
    // 拿到当前下标的评论
    let afterComment = comments.value[index1].childComments[index2]
    afterComment.isShowReplyForm = !beforeComment
  }
  // 动态设置评论回复表单中的 textarea 的 placeholder 提示文字
  replyPlaceholderText.value = '回复 @' + nickname + ':'
  nextTick(() => {
    initPopovers(), initTooltips()
  })
}
// 展示子回复
const showChildReply = (index) => {
  // 先将评论数组中一级评论的所有 isShowReplyForm 字段设置为 false
  let beforeComment = comments.value[index].expanded
  comments.value.forEach(c => {
    c.expanded = false
  })

  // TODO 获取子评论
  refreshSonComment(index)
  comments.value[index].expanded = !beforeComment
}

</script>
<style scoped>
.comment-box{ padding:12px; background:#fafafa; }
.comment-item{ margin-bottom:8px; line-height:1.6; }
.load-more,.no-more,.loading{ text-align:center; padding:10px; color:#666; cursor:pointer; }
.load-more:hover{ color:#409eff; }
</style>