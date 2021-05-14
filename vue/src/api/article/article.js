import {vilceServiceApi} from '@/utils/request'

// 根据ID删除文章
export function deleteArticle(id) {
  return vilceServiceApi({
    url: '/article/deleteArticle/' + id,
    method: 'get'
  })
}

// 获取某标签文章
export function getArticleByLabel(pageIndex, pageSize, label) {
  return vilceServiceApi({
    url: '/article/getArticleByLabel/' + pageIndex + '/' + pageSize,
    method: 'get',
    params: {label}
  })
}

// 获取某类文章
export function getArticleByType(pageIndex, pageSize, type) {
  return vilceServiceApi({
    url: '/article/getArticleByType/' + pageIndex + '/' + pageSize,
    method: 'get',
    params: {type}
  })
}

// 根据ID获取文章
export function getOneArticle(id) {
  return vilceServiceApi({
    url: '/article/getOneArticle/' + id,
    method: 'get'
  })
}

// 分页展示文章信息
export function listArticles(pageIndex, pageSize) {
  return vilceServiceApi({
    url: '/article/listArticles/' + pageIndex + '/' + pageSize,
    method: 'get'
  })
}

// 添加或更新文章信息
export function saveArticle(data) {
  return vilceServiceApi({
    url: '/article/saveArticle',
    method: 'post',
    data
  })
}

// 模糊查询文章
export function search(searchStr) {
  return vilceServiceApi({
    url: '/article/search',
    method: 'get',
    params: { searchStr }
  })
}

// 模糊查询文章
export function statistics() {
  return vilceServiceApi({
    url: '/article/statistics',
    method: 'get'
  })
}


