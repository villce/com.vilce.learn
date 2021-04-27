import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/',
    name: 'Default',
    redirect: '/home',
    component: Home
  },
  {
    // home页面并不需要被访问，只是作为其它组件的父组件
    path: '/home',
    name: 'Home',
    component: () => import('../components/Home')
  },
  {
    path: '/index',
    name: 'Index',
    component: () => import('../components/Home')
  },
  {
    path: '/tool',
    name: 'Tool',
    component: () => import('../components/home/tool/Tool'),
    children: [
      {
        path: '/tool/markTool',
        name: 'MarkTool',
        component: () => import('../components/home/tool/markImage/MarkTool')
      },
      {
        path: '/tool/certTool',
        name: 'CertTool',
        component: () => import('../components/home/tool/certImage/CertTool')
      }
    ]
  },
  {
    path: '/secret',
    name: 'Secret',
    component: () => import('../components/home/secret/Secret')
  },
  {
    path: '/jotter',
    name: 'Jotter',
    component: () => import('../components/home/jotter/Articles')
  },
  {
    path: '/jotter/article',
    name: 'Article',
    component: () => import('../components/home/jotter/ArticleDetails')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../components/home/user/Login')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../components/home/user/Register')
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../components/admin/AdminIndex'),
    meta: {
      requireAuth: true
    },
    children: [
      {
        path: '/admin/dashboard',
        name: 'Dashboard',
        component: () => import('../components/admin/dashboard/admin/index'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/admin/content/articleEditor',
        name: 'ArticleEditor',
        component: () => import('../components/admin/content/jotter/ArticleEditor'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/admin/content/secretModulesEditor',
        name: 'SecretModulesEditor',
        component: () => import('../components/admin/content/secret/SecretModulesEditor'),
        meta: {
          requireAuth: true
        }
      },
    ]
  },
  {
    path: '*',
    component: () => import('../components/pages/Error404')
  }
]

// 用于创建默认路由
export const createRouter = () => new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
