<template>
	<div>
		<el-row>
		  <el-col :span="5" v-for="item in customerList" style="margin: 33px;">
		    <el-card>
		    	<div slot="header">
		    		<img src="@/assets/05.jpg" class="image">
		    		<span float="left">{{item.customer_nickname}}</span>
		    	</div>
		      <div style="padding: 14px;">
		        <!-- <span>{{item.name}}</span> -->
		        <div class="bottom clearfix">
		          <el-button type="text" class="button" @click="handleAdd(item.customer_id)">添加好友</el-button>
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
			customerList:[
				// {customer_id:1,customer_nickname:"hasj",},
				// {customer_id:2,customer_nickname:"55",},
				// {customer_id:3,customer_nickname:"hafsj",},
				// {customer_id:4,customer_nickname:"hsdfasj",},
				// {customer_id:5,customer_nickname:"hasfsaj",},
				// {customer_id:6,customer_nickname:"haffsj",},
			]
		}
	},
	methods:{
		handleAll:function(){
			var content = this.$route.query.content
			var key={}
			var me=this
			key.content=content
			this.$post("/customer/select",key)
	        .then(res=>{
	          me.customerList=res.data.customerList
	      }).catch(function(err){
	       console.log(err)
	      })
		},
		handleAdd: function(customer_id){
			var friend_id=customer_id
			customer_id=localStorage.customer_id
			var key={}
			var me=this
			key.customer_id=customer_id
			key.friend_id=friend_id
			this.$post("/friend/add",key)
	        .then(res=>{
	          if(res.data.error==0){
	            this.$notify({title: '成功',message: "已发送申请",duration: 3000})
	          } else {
	            this.$notify({title: '失败',message: res.data.message,duration: 6000})
	          }
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
    width: 40%;
    display: block;
    float: left;
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