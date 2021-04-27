import Vue from 'vue'
import App from './App'
import router from './router'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import 'echarts/theme/macarons.js'
import store from './store'

import {
  Pagination,
  Dialog,
  Menu,
  Submenu,
  MenuItem,
  MenuItemGroup,
  Input,
  Checkbox,
  CheckboxButton,
  CheckboxGroup,
  DropdownItem,
  Switch,
  Select,
  Option,
  Button,
  ButtonGroup,
  Table,
  TableColumn,
  Tooltip,
  Breadcrumb,
  BreadcrumbItem,
  Form,
  FormItem,
  Tabs,
  TabPane,
  Tag,
  Tree,
  Alert,
  Icon,
  Row,
  Col,
  Upload,
  Progress,
  Spinner,
  Badge,
  Card,
  Rate,
  Steps,
  Step,
  Carousel,
  CarouselItem,
  Container,
  Header,
  Aside,
  Main,
  Footer,
  Timeline,
  TimelineItem,
  Link,
  Divider,
  Image,
  Loading,
  MessageBox,
  Message,
  Notification, Dropdown, DropdownMenu, ColorPicker, Cascader, Slider, Avatar,
  DatePicker, Collapse, CollapseItem, RadioGroup, RadioButton, Radio,
} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(Pagination)
Vue.use(Dialog)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(MenuItemGroup)
Vue.use(Input)
Vue.use(Checkbox)
Vue.use(CheckboxButton)
Vue.use(CheckboxGroup)
Vue.use(Switch)
Vue.use(Select)
Vue.use(Option)
Vue.use(Button)
Vue.use(ButtonGroup)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Tooltip)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Tag)
Vue.use(Tree)
Vue.use(Alert)
Vue.use(Icon)
Vue.use(Row)
Vue.use(Col)
Vue.use(Upload)
Vue.use(Progress)
Vue.use(Spinner)
Vue.use(Badge)
Vue.use(Card)
Vue.use(Rate)
Vue.use(Steps)
Vue.use(Step)
Vue.use(Carousel)
Vue.use(CarouselItem)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Footer)
Vue.use(Timeline)
Vue.use(TimelineItem)
Vue.use(Link)
Vue.use(Divider)
Vue.use(Image)
Vue.use(Dropdown)
Vue.use(DropdownItem)
Vue.use(DropdownMenu)
Vue.use(ColorPicker)
Vue.use(Cascader)
Vue.use(Option)
Vue.use(Slider)
Vue.use(Avatar)
Vue.use(Loading.directive)
Vue.use(DatePicker)
Vue.use(Collapse)
Vue.use(CollapseItem)
Vue.use(RadioGroup)
Vue.use(RadioButton)
Vue.use(Radio)

Vue.prototype.$loading = Loading.service
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$alert = MessageBox.alert
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$prompt = MessageBox.prompt
Vue.prototype.$notify = Notification
Vue.prototype.$message = Message

var axios = require('axios')
axios.defaults.baseURL = "http://localhost:8006/api"
// axios.defaults.baseURL = "http://Vilce-SpringBoot-Vue/api"
// 使请求带上凭证信息
axios.defaults.withCredentials = true

Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(mavonEditor)

router.beforeEach((to, from, next) => {
  // 已登录后访问admin下页面，初始化菜单
  if (store.state.username && to.path.startsWith('/admin')) {
    initAdminMenu(router, store)
  }
  if (store.state.username && to.path.startsWith('/login')) {
    next({
      name: 'Dashboard'
    })
  }
  // 如果前端没有登录信息则直接拦截，如果有则判断后端是否正常登录（防止构造参数绕过）
  if (to.meta.requireAuth) {
    if (store.state.username) {
      axios.get('/login/authentication').then(resp => {
        if (resp) {
          next()
        }
      })
    } else {
      next({
        path: 'login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
})

// http response 拦截器
axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    if (error) {
      store.commit('logout')
      router.replace('/')
    }
    // 返回接口返回的错误信息
    return Promise.reject(error)
  })

const initAdminMenu = (router, store) => {
  // 防止重复触发加载菜单操作
  if (store.state.adminMenus.length > 0) {
    return
  }
  axios.get('/menu/getUserMenu').then(resp => {
    if (resp && resp.data.status === 0) {
      var fmtRoutes = formatRoutes(resp.data.data)
      router.addRoutes(fmtRoutes)
      store.commit('initAdminMenu', fmtRoutes)
    }
  })
}

const formatRoutes = (routes) => {
  let fmtRoutes = []
  routes.forEach(route => {
    console.info(route)
    if (route.children) {
      route.children = formatRoutes(route.children)
    }

    let fmtRoute = {
      path: route.path,
      component: resolve => {
        require(['./components/admin/' + route.component + '.vue'], resolve)
      },
      name: route.name,
      name_zh: route.name_zh,
      icon_cls: route.icon_cls,
      meta: {
        requireAuth: true
      },
      children: route.children
    }
    fmtRoutes.push(fmtRoute)
  })
  return fmtRoutes
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: {App},
  template: '<App/>'
})
