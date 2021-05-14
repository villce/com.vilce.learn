//前端数组分页
export function pagination(pageNum, pageSize, array) {
    let offset = (pageNum - 1) * pageSize;
    return (offset + pageSize >= array.length) ? array.slice(offset, array.length) : array.slice(offset, offset + pageSize);
}
