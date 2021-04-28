import request from '../../utils/request'

// 根据ID删除文章
export function deleteArticle(id) {
  return request({
    url: '/article/deleteArticle/' + id,
    method: 'get'
  })
}

// 获取某标签文章
export function getArticleByLabel(label) {
  return request({
    url: '/article/getArticleByLabel',
    method: 'get',
    params: {label}
  })
}

// 获取某类文章
export function getArticleByType(type) {
  return request({
    url: '/article/getArticleByType',
    method: 'get',
    params: {type}
  })
}

// 根据ID获取文章
export function getOneArticle(id) {
  return request({
    url: '/article/getOneArticle/' + id,
    method: 'get'
  })
}

// 分页展示文章信息
export function listArticles(pageIndex, pageSize) {
  return request({
    url: '/article/listArticles/' + pageIndex + '/' + pageSize,
    method: 'get'
  })
}

// 添加或更新文章信息
export function saveArticle(data) {
  return request({
    url: '/article/saveArticle',
    method: 'post',
    data
  })
}

// 模糊查询文章
export function search(searchStr) {
  return request({
    url: '/article/search',
    method: 'get',
    params: { searchStr }
  })
}

// 模糊查询文章
export function statistics() {
  return request({
    url: '/article/statistics',
    method: 'get'
  })
}


