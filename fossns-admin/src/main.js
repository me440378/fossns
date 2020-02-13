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

/* 时间格式化 */
Vue.prototype.$filter=Vue.filter('date',function(input,e,k){
var oDate=new Date(input);
if(k){
  oDate = +oDate + 1000*60*60*24*k;
  oDate = new Date(oDate);
  }
  var Year=oDate.getFullYear();
  var Month=(oDate.getMonth()+1);
  var Day=oDate.getDate();
  var Hours=oDate.getHours();
  var Minutes=oDate.getMinutes();
  var Seconds=oDate.getSeconds();
  return Year+'-'+Month+'-'+Day+' '+Hours+':'+Minutes+':'+Seconds;
});

/* 页面路由 */
import Basic from './pages/Basic.vue'
import Customers from './pages/Customers.vue'
import JoinGroup from './pages/JoinGroup.vue'
import Groups from './pages/Groups.vue'
import CreateGroup from './pages/CreateGroup.vue'
import UpdateGroup from './pages/UpdateGroup.vue'
import Topics from './pages/Topics.vue'
import CreateTopic from './pages/CreateTopic.vue'
import Complaints from './pages/Complaints.vue'
import GroupReports from './pages/GroupReports.vue'
import TopicReports from './pages/TopicReports.vue'
import Banners from './pages/Banners.vue'

const routes = [
	{path: '/',redirect:'/Basic'},
	{path: '/Basic',name: 'Basic',component: Basic},
	{path: '/Customers',name: 'Customers',component: Customers},
	{path: '/JoinGroup',name: 'JoinGroup',component: JoinGroup},
	{path: '/Groups',name: 'Groups',component: Groups},
	{path: '/CreateGroup',name: 'CreateGroup',component: CreateGroup},
	{path: '/UpdateGroup',name: 'UpdateGroup',component: UpdateGroup},
	{path: '/Topics',name: 'Topics',component: Topics},
	{path: '/CreateTopic',name: 'CreateTopic',component: CreateTopic},
	{path: '/Complaints',name: 'Complaints',component: Complaints},
	{path: '/GroupReports',name: 'GroupReports',component: GroupReports},
	{path: '/TopicReports',name: 'TopicReports',component: TopicReports},
	{path: '/Banners',name: 'Banners',component:Banners}
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
