<template>
	<div>
		<div class="block">
	    	<el-carousel :interval="4000" type="card" height="300px">
	     		<el-carousel-item v-for="item in bannerList" style="text-align: center;">
	     		<img :src="item.image" style="width: 640px;height: 300px;"/>
	     			<div style="width: 640px;height: 30px;position: absolute;left: 0;bottom: 0px;">
	     				<span>{{ item.title }}</span>
	     			</div>
	      	</el-carousel-item>
	    	</el-carousel>
	  	</div>
	  	<el-row>
			<el-row><span>近期官方活动</span></el-row>
			<el-row v-for="item in activityList" style="padding: 30px 30px 30px 30px;">
				<el-col :span="6">
					<el-card class="box-card" style="width: 100%;height: 300px;">
						<div slot="header" class="clearfix">
						    <span>{{ item.name }}</span>
						</div>
		  				<div>
							<img :src="item.image" style="width:100%;display: block;"/>
		  				</div>
					</el-card>
				</el-col>
				<el-col :span="18">
					<el-card class="box-card" style="width: 100%;height: 300px;">
						<div slot="header" class="clearfix">
						    <span>活动简介</span>
						    <el-button style="float: right; padding: 3px 0" type="text">进入</el-button>
						</div>
		  				<div style="text-align: left;">
							<span>{{item.summary}}</span>
		  				</div>
					</el-card>
				</el-col>
			</el-row>
	  	</el-row>
	</div>
</template>
<script>
export default{
	data(){
		return{
			bannerList:[
				// {image:'http://localhost:8010/849888149f8cd08adca46aa11aa09efc.png',title:1111111},
				// {image:'http://localhost:8010/849888149f8cd08adca46aa11aa09efc.png',title:22222222},
				// {image:'http://localhost:8010/849888149f8cd08adca46aa11aa09efc.png',title:3333333333}
			],
			activityList:[
				// {name:'活动1',image:'http://localhost:8010/849888149f8cd08adca46aa11aa09efc.png',summary:'活动1活动简介'},
				// {name:'活动2',image:'http://localhost:8010/849888149f8cd08adca46aa11aa09efc.png',summary:'活动2活动简介'},
				// {name:'活动3',image:'http://localhost:8010/849888149f8cd08adca46aa11aa09efc.png',summary:'活动3活动简介'},
			]
		}
	},
	methods:{
		handleAll:function(){
			var key = {}
			var me = this
			this.$post("/home/all",key)
          .then(res=>{
                 me.bannerList = res.data.bannerList
                 me.activityList = res.data.activityList
      }).catch(function(err){
       console.log(err)
      });
		}
	},mounted:function(){
		this.handleAll();
	}
}
</script>
<style scoped>
</style>