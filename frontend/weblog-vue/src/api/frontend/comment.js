import axios from "@/axios.js"

// 获取评论
export function getComment(data){
    return axios.get("/article/comment/getComment",data)
}

// 提交评论
export function addComment(data){
    return axios.post("/article/comment/addComment",data)
}

// 删除评论
export function deleteComment(id){
    return axios.post("/article/comment/deleteComment",id)
}

// 点赞评论
export function likeComment(id){
    return axios.post("/article/comment/likeComment",id)
}