<template>
<div>
	<el-tabs type="card" tab-position="left">
	    <el-tab-pane label="最热话题" style="background-color: #ccffee;">
			<el-card class="box-card" v-for="item,no in hotTopicList" style="">
				<div slot="header" class="clearfix">
				    	<span style="float: left;">{{no+1}}  {{item.group_name}}</span>
						<el-button @click="handleJumpGroup(item.group_id)" style="float: right; padding: 0px;" type="text">进入圈子</el-button>
				</div>
  				<div>
  					<el-row>
  						<el-col :span="18">
							<span style="float: left;">{{item.topic_name}}</span>
						</el-col>
						<el-col :span="6">
							<el-button @click="handleJumpTopic(item.topic_id)" style="float: right;padding: 0px;" type="text">进入话题</el-button>
						</el-col>
					</el-row>
				    <span style="float: left;">{{item.topic_summary}}</span>
				    <img src="item.topic_image"/>
  				</div>
			</el-card>
	    </el-tab-pane>

	    <el-tab-pane label="最新话题" style="background-color: #ccffee;">
	    	<el-card class="box-card" v-for="item,no in newTopicList" style="">
				<div slot="header" class="clearfix">
				    	<span style="float: left;">{{no+1}}  {{item.group_name}}</span>
						<el-button @click="handleJumpGroup(item.group_id)" style="float: right;padding: 0px;" type="text">进入圈子</el-button>
				</div>
  				<div>
  					<el-row>
  						<el-col :span="18">
							<span style="float: left;">{{item.topic_name}}</span>
						</el-col>
						<el-col :span="6">
							<el-button @click="handleJumpTopic(item.topic_id)" style="float: right;padding: 0px;" type="text">进入话题</el-button>
						</el-col>
					</el-row>
				    <span style="float: left;">{{item.topic_summary}}</span>
				    <img src="item.topic_image"/>
  				</div>
			</el-card>
	    </el-tab-pane>
  	</el-tabs>
</div>
</template>
<script>
export default{
	data(){
		return{
			hotTopicList:[
				// {group_name:"圈子1",group_id:1,topic_name:"话题1",topic_id:1,topic_summary:"简简介简介简介简介简介简介简介介",topic_image:""},
				// {group_name:"圈子2",group_id:2,topic_name:"话题2",topic_id:2,topic_summary:"简简介简介简介简介ss简介简fgs介简介介",topic_image:""}
			],
			newTopicList:[
				// {group_name:"ss圈子1",group_id:1,topic_name:"话ss题1",topic_id:1,topic_summary:"简简介简介简ss介简介简介简介简介介",topic_image:""},
				// {group_name:"圈子ss2",group_id:2,topic_name:"话ss题2",topic_id:2,topic_summary:"简简介简介简ss介简介ss简介简fgs介简介介",topic_image:""}
			]
		}
	},
	methods:{
		handleJumpGroup:function(group_id){
			this.$router.push("/Group/" + group_id)
		},
		handleJumpTopic:function(topic_id){
			this.$router.push("/Topic/" + topic_id)
		},
		handleAll:function(){
			var key={}
			var me=this
			this.$post("/topiclist/all",key)
      .then(res=>{
	      me.hotTopicList = res.data.hotTopicList
	      me.newTopicList = res.data.newTopicList
      }).catch(function(err){
       console.log(err)
      });
		}
	},
	mounted:function(){
		this.handleAll()
	}
}
</script>
<style scoped>
</style>