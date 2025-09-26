import axios from "@/axios";

// 获取文章归档分页数据
export function getArchivePageList(data) {
    return axios.post("weblog/article/archive/list", data)
}