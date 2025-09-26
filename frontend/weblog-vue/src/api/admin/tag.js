import axios from "@/axios";



// 获取分类分页数据
export function getTagPageList(data) {
    return axios.post("weblog/users/tag/list", data)
}
// 获取所有标签
export function searchTags(data) {
    return axios.post("weblog/users/tag/search", data)
}
// 添加分类
export function addTag(data) {
    return axios.post("weblog/users/tag/add", data)
}
// 删除分类
export function deleteTag(id) {
    return axios.delete("weblog/users/tag/delete/" + id)
}
// 获取标签 select 列表数据
export function getTagSelectList(data) {
    return axios.get("weblog/users/tag/select/list")
}