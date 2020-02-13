<template>
	<div>
	  <el-card v-for="item in topicList" style="margin: 20px;">
	    <!-- <img src="@/assets/03.jpg" class="image"> -->
	    <el-row>
				<el-col :span="12">
					<span style="float: left;">{{item.name}}</span>
				</el-col>
				<el-col :span="6">
					<span style="float: left;">人气：{{item.popularity}}</span>
				</el-col>
				<el-col :span="3">
					<span style="float: right;font-size: 13px;color: #999;">创建人：{{item.creator_nickname}}</span>
				</el-col>
				<el-col :span="3">
					<el-button @click="handleJumpTopic(item.topic_id)" style="float: right;padding: 0px;" type="text">进入话题</el-button>
				</el-col>
			</el-row>
	  </el-card>
	</div>
</template>
<script>
export default{
	data(){
		return{
			topicList:[
				// {topic_id:1,name:'话题1',popularity:8000,creator_nickname:"阿鸡"},
				// {topic_id:2,name:'话题2',popularity:5000,creator_nickname:"阿猪"},
				// {topic_id:3,name:'话题3',popularity:1000,creator_nickname:"阿鸭"}
			]
		}
	},
	methods:{
		handleJumpTopic:function(topic_id){
			this.$router.push("/Topic/" + topic_id)
		},
		handleAll:function(){
			var content = this.$route.query.content
			var key={}
			var me=this
			key.content=content
			this.$post("/topic/select",key)
	        .then(res=>{
	          me.topicList=res.data.topicList
	      }).catch(function(err){
	       console.log(err)
	      })
		}
	},
	watch: {
	   	'$route': 'handleAll'
	},
	mounted:function(){
		this.handleAll()
	}
}
</script>
<style scoped>
.popularity {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>