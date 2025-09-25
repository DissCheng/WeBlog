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
                      :placeholder="replyArticlePlaceholderText" required></textarea>
          </div>
          <div class="flex items-center justify-between px-3 py-2 border-t dark:border-gray-600">
            <div class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white
bg-sky-600 rounded-lg focus:ring-4 focus:ring-sky-200 dark:focus:ring-sky-900 hover:bg-sky-700">
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
            <div v-if="comment.childCommentsCnt > 0"
                 class="text-xs text-gray-400 cursor-pointer ml-4 hover:text-sky-600">
              <!-- 二级评论回复 -->
              <div class="text-gray-400 cursor-pointer ml-4 hover:text-sky-600"
                   @click="showChildReply(index)">
                {{ comment.expanded ? '收起回复' : `展开全部${comment.childCommentsCnt}条回复` }}
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
                        :placeholder="replyPlaceholderText" required></textarea>
            </div>
            <div class="flex items-center justify-between px-3 py-2 border-t dark:border-gray-600">
              <div class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white
bg-sky-600 rounded-lg focus:ring-4 focus:ring-sky-200 dark:focus:ring-sky-900 hover:bg-sky-700">
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
              <div class="text-xs text-[#FB7299] font-bold">{{ childComment.nickname }}</div>
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
                            :placeholder="replyPlaceholderText" required></textarea>
                </div>
                <div class="flex items-center justify-between px-3 py-2 border-t dark:border-gray-600">
                  <div class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white
bg-sky-600 rounded-lg focus:ring-4 focus:ring-sky-200 dark:focus:ring-sky-900 hover:bg-sky-700">
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
    </div>
  </div>
</template>


<script setup>
import {ref, reactive, onMounted, nextTick} from 'vue'
import {initPopovers, initTooltips} from "flowbite";
import {getComment} from "@/api/frontend/comment.js";
import {useRoute, useRouter} from "vue-router";

let total = ref(0)
//当前路由
const route = useRoute()

// 评论数组
const comments = ref([
  {

    "id": 39818,
    "avatar": "https://qh.qlogo.cn/g?b=sdk&ek=AQCCUxlJ6SzcyLnoceicwiaOoqHLfHPDJ48xj7dsbLzfP3LPC0YFw9ibWW1UaIicy2vIIZvUpLDf&s=100&t=1690161385",
    "nickname": "嘻嘻",
    "website": null,
    "content": "头像和昵称可以自动获取了\uD83D\uDE03",
    "createTime": "2024-03-09 09:33:46",
    "replyNickname": null,
    "childCommentsCnt": 0,
    "childComments": [],
    "expanded": false,
    "isShowReplyForm": null
  },
  {
    "id": 39813,
    "avatar": "https://qh.qlogo.cn/g?b=sdk&ek=AQDW4PNnLEibrRIicSnnZZrBD2siaNCiaiaQec1LEccHQRM7IJun9EAoLh4vU0iasibTHjTKbCFic1VC&s=100&t=1593000563",
    "nickname": "SYD",
    "website": null,
    "content": "观望\uD83E\uDD11",
    "createTime": "2024-03-06 13:41:56",
    "replyNickname": null,
    "childCommentsCnt": 2,
    "expanded": false,
    "childComments": [
      {
        "id": 39814,
        "avatar": "https://qh.qlogo.cn/g?b=sdk&ek=AQAJ99lfkKA9TldbMpTOicsyfib30JOuNnqdaFehfOzYz8qEQo3f7JbY24mCKYoZ5meJibLeuDq&s=100&t=1681803828",
        "nickname": "DissCheng",
        "website": null,
        "content": "欢迎欢迎\uD83D\uDE0E",
        "createTime": "2024-03-07 11:52:30",
        "replyNickname": null,
        "childComments": null,
        "childCommentsCnt": 0,
        "expanded": false,
        "isShowReplyForm": null
      },
      {
        "id": 39815,
        "avatar": "https://qh.qlogo.cn/g?b=sdk&ek=AQAJ99lfkKA9TldbMpTOicsyfib30JOuNnqdaFehfOzYz8qEQo3f7JbY24mCKYoZ5meJibLeuDq&s=100&t=1681803828",
        "nickname": "Diss",
        "website": null,
        "content": "欢迎欢迎\uD83D\uDE0E",
        "createTime": "2024-03-07 11:52:30",
        "replyNickname": null,
        "childComments": null,
        "childCommentsCnt": 0,
        "expanded": false,
        "isShowReplyForm": null
      }
    ],
    "isShowReplyForm": null
  }
])


//获取文章一级评论
function refreshComment() {
  getComment({
    articleId: route.params.articleId,
    isPrimary: true,
  }).then((res) => {
    comments.value = res.data
    total = comments.value.length
  })
}

//获取一级评论的二级评论
function refreshSonComment(index) {
  getComment({
    articleId: route.params.articleId,
    replyId: comments.value[index].id,
    isPrimary: false,
  }).then((res) => {
    comments.value = res.data
    total = comments.value.length
  })
}

//提交评论
function addComment(index1, index2) {
  let replyId = -1;
  let isPrimary = false;
  if (index1 === -1) {
    isPrimary = true;
  } else {
    if (index2 === -1) {
      replyId = comments.value[index1].id;
    }else{
      replyId = comments.value[index1].childComments[index2].id;
    }
    isPrimary = false;
  }
  addComment({
    articleId: route.params.articleId,
    replyId: comments.value[index].id,
    isPrimary: false,
  }).then((res) => {

  })
}

// 回复 textarea 的 placeholder 提示文字
const replyArticlePlaceholderText = ref('发表一个友善的评论吧...')
const replyPlaceholderText = ref('发表一个友善的评论吧...')
// 展示回复表单
const showReplyForm = (index1, index2, nickname, replyCommentId, parentCommentId) => {
  // 先将评论数组中一级评论的所有 isShowReplyForm 字段设置为 false
  let beforeComment = 0;
  if (index2 === -1) {
    //一级评论
    beforeComment = comments.value[index1].isShowReplyForm;
  } else {
    //二级评论
    beforeComment = comments.value[index1].childComments[index2].isShowReplyForm;
  }
  comments.value.forEach(c => {
    c.isShowReplyForm = false;
    c.childComments.forEach(child => {
      child.isShowReplyForm = false;
    });
  });
  if (index2 === -1) {
    // 拿到当前下标的评论
    let afterComment = comments.value[index1]
    afterComment.isShowReplyForm = !beforeComment
  } else {
    // 拿到当前下标的评论
    let afterComment = comments.value[index1].childComments[index2];
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
  // TODO 获取子评论
  comments.value[index].expanded = !comments.value[index].expanded;
}

</script>


<style scoped>

</style>