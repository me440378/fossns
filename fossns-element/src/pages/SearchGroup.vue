<template>
	<div>
		<el-row>
		  <el-col :span="7" v-for="item in groupList" style="margin: 30px;">
		    <el-card>
		      <img :src="item.image" style="width: 100%" class="image">
		      <div style="padding: 14px;">
		        <span>{{item.name}}</span>
		        <div class="bottom clearfix">
		        	<span class="popularity">人气{{ item.popularity }}</span>
		          <el-button type="text" class="button" @click="handleJump(item.group_id)">进入圈子</el-button>
		        </div>
		      </div>
		    </el-card>
		  </el-col>
		</el-row>
	</div>
</template>
<script>
export default{
	data(){
		return{
			groupList:[
				// {group_id:1,name:'圈子1',popularity:10000},
				// {group_id:2,name:'圈子2',popularity:5000},
				// {group_id:3,name:'圈子3',popularity:1000}
			]
		}
	},
	methods:{
		handleJump:function(group_id){
			this.$router.push("/Group/" + group_id);
		},
		handleAll:function(){
			var content = this.$route.query.content;
			var key={}
			var me=this
			key.content=content
			this.$post("/group/select",key)
	        .then(res=>{
	          me.groupList=res.data.groupList
	      }).catch(function(err){
	       console.log(err)
	      })
		}
	},
  watch: {
    '$route': 'handleAll'
  },
	mounted:function(){
		this.handleAll();
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