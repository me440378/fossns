import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import AppLogin from './login.vue'

Vue.use(VueRouter)
Vue.use(ElementUI)

/* HTTP请求 */
var Axios = axios;

Vue.prototype.baseUrl="http://localhost:8080/fossns"
Vue.prototype.toParams=function (obj) {
  var param = ""
  for(const name in obj) {
    if(typeof obj[name] != 'function') {
        param += "&" + name + "=" + encodeURI(obj[name])
    }
  }
  return param.substring(1)
}

Vue.prototype.$post=function(api,data){
	var url = this.baseUrl + api
	if (data === undefined) {
    data = {};
  } else {
  	url += '?'+this.toParams(data)
  }
  return Axios.post(url)
}

Vue.prototype.$get=function(api,data){
	if (data === undefined) {
    data = {};
  }
  var url = this.baseUrl + api
  return Axios.get(url,{params:data})
}

/* 添加路由 */
import Home from './pages/Home.vue'
import GroupList from './pages/GroupList.vue'
import TopicList from './pages/TopicList.vue'
import MyFriend from './pages/MyFriend.vue'
import Chat from './pages/Chat.vue'
import MyInformation from './pages/MyInformation.vue'
import SearchGroup from './pages/SearchGroup.vue'
import SearchTopic from './pages/SearchTopic.vue'
import SearchCustomer from './pages/SearchCustomer.vue'
import Group from './pages/Group.vue'
import Topic from './pages/Topic.vue'
import ChargeGroup from './pages/ChargeGroup.vue'

const routes = [
	{path: '/',redirect:'/Home'},
	{path: '/Home',name: 'Home',component: Home},
	{path: '/GroupList',name: 'GroupList',component: GroupList},
	{path: '/TopicList',name: 'TopicList',component: TopicList},
	{path: '/MyFriend',name: 'MyFriend',component: MyFriend},
	{path: '/MyInformation',name: 'MyInformation',component: MyInformation},

	{path: '/SearchGroup',name: 'SearchGroup',component:SearchGroup},
	{path: '/SearchTopic',name: 'SearchTopic',component:SearchTopic},
	{path: '/SearchCustomer',name: 'SearchCustomer',component:SearchCustomer},
	{path: '/Group/:group_id',name: 'Group',component:Group},
	{path: '/Topic/:topic_id',name: 'Topic',component:Topic},
	{path: '/Chat/:customer_id',name:'Chat',component:Chat},
	{path: '/ChargeGroup/:group_id',name:'ChargeGroup',component:ChargeGroup}
]

const router = new VueRouter({routes: routes})

new Vue({
	router: router,
	el: '#app',
	render: h => {
    if(window.location.href.split('#')[1]=='/login'){
      return h(AppLogin);
    }
    return h(App);
  }
})
