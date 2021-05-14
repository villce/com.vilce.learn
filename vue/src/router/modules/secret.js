/** When your routing table is too long, you can split it into small modules **/

const secretRouter = {
    path: '/secret',
    name: 'Secret',
    component: () => import('@/views/secret/Secret')
}

export default secretRouter
