import axios from "@/axios";

// 获取分类分页数据
export function getCategoryPageList(data) {
    return axios.post("weblog/users/category/list", data)
}
// 添加分类
export function addCategory(data) {
    return axios.post("weblog/users/category/add", data)
}
// 删除分类
export function deleteCategory(id) {
    return axios.delete("weblog/users/category/delete/" + id)
}
// 获取分类 select
export function getCategorySelectList() {
    return axios.get("weblog/users/category/listAll")
}