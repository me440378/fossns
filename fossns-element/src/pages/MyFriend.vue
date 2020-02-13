<template>
<div>
	<el-tabs type="card" tab-position="left">
		<el-tab-pane label="好友动态">
			<el-row>
				<el-col :span="24">
					<el-card class="box-card">
						<div slot="header"><span style="float: left;padding: 3px;">发送动态</span></div>
						<div>
						<el-input type="textarea" :rows="3" placeholder="请输入内容" v-model="form.content"></el-input>
							<!-- <el-upload class="upload-demo" action="https://jsonplaceholder.typicode.com/posts/" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList2" list-type="picture">
								<el-button size="small" type="primary">上传图片</el-button>
								<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
							</el-upload> -->
						<el-button type="primary" style="margin: 10px;" @click="handleCreateDynamic">发表</el-button>
						</div>
					</el-card>
				</el-col>
				<el-col :span="24">
					<span>好友动态</span>
				</el-col>
				<el-col :span="24" v-for="item in dynamicList" style="padding: 10px 10px 10px 10px;">
					<el-card class="box-card">
						<el-row>
							<el-col :span="2">
							<img src="item.customer_icon"/>
							</el-col>
							<el-col :span="22">
								<el-row>
									<el-col :span="22"><span style="float: left;">{{item.customer_nickname}}</span></el-col>
								</el-row>
								<el-row>
									<el-col :span="22"><span style="float: left;font-color:#aaa;">{{item.dynamic_created_at}}</span></el-col>
								</el-row>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="24"><span style="float: left;font-color:#999;">{{item.dynamic_content}}</span></el-col>
						</el-row>
						<el-row>
							<el-col :span="24"><img src="item.dynamic_image"/></el-col>
						</el-row>
					</el-card>
				</el-col>
			</el-row>
		</el-tab-pane>

		<el-tab-pane label="好友列表">
			<el-card class="box-card" v-for="item in friendList">
				<el-row>
					<el-col :span="2">
					<!-- <img src="item.customer_icon"/> -->
					</el-col>
					<el-col :span="6"><span style="float: left;">{{item.customer_nickname}}</span></el-col>
						<el-col :span="16">
						<div style="float: right;">
							<el-button @click="handleJumpChat(item.customer_id)">进入私信</el-button>
							<el-button @click="handleDelete(item.customer_id)">删除好友</el-button>
						</div>
					</el-col>
				</el-row>
			</el-card>
		</el-tab-pane>

		<el-tab-pane label="好友请求">
			<el-card class="box-card" v-for="item in applyList">
				<el-row>
					<el-col :span="2">
					<!-- <img src="item.customer_icon"/> -->
					</el-col>
					<el-col :span="6"><span style="float: left;">{{item.customer_nickname}}</span></el-col>
					<el-col :span="16">
						<div style="float: right;">
							<el-button @click="handleAgreeApply(item.customer_id,item.apply_id)">同意</el-button>
							<el-button @click="handleRejectApply(item.apply_id)">拒绝</el-button>
						</div>
					</el-col>
				</el-row>
			</el-card>
		</el-tab-pane>
	</el-tabs>
</div>
</template>
<script>
export default{
	data(){
		return{
			form:{
				content:"",
				image:""
			},
			// fileList2: [
			// 	{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, 
			// 	{name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}
			// ],
			dynamicList:[
				// {customer_icon:"",customer_nickname:"用户1",dynamic_created_at:"2018-06-03 00:14",dynamic_content:"内容sadasdasd",dynamic_image:""},
				// {customer_icon:"",customer_nickname:"用户2",dynamic_created_at:"2018-06-03 00:19",dynamic_content:"内容sadaasdasdassdasd",dynamic_image:""}
			],
			friendList:[
				// {customer_id:1,customer_nickname:"优惠价"},
				// {customer_id:2,customer_nickname:"用户已"},
				// {customer_id:3,customer_nickname:"一会吧"}
			],
			applyList:[
				// {customer_id:1,customer_nickname:"优惠d价"},
				// {customer_id:2,customer_nickname:"用户s已"},
				// {customer_id:3,customer_nickname:"一会f吧"}
			]
		}
	},
	methods:{
	// handleRemove(file, fileList) {
 //        console.log(file, fileList);
 //    },
 //    handlePreview(file) {
 //        console.log(file);
 //    },
    handleJumpChat:function(customer_id){
    	var sender_id=localStorage.customer_id
    	this.$router.push({
    		path:"/Chat/" + sender_id,
    		query:{
    			receiver_id:customer_id
    		}
    	});
    },
    handleDelete:function(customer_id){
    	var friend_id = customer_id
    	customer_id=localStorage.customer_id
    	var me=this
    	var key ={}
    	key.friend_id=friend_id
    	key.customer_id=customer_id
    	this.$post("/friend/delete",key)
    	.then(res=>{
    		if(res.data.error==0){
          	this.$notify({title: '成功',message: "成功删除好友",duration: 3000})
          } else {
          	this.$notify({title: '失败',message: res.data.message,duration: 3000})
          }
    	}).catch(function(err){
		     console.log(err)
		    });
    	this.handleAll()
    },
    handleAgreeApply:function(customer_id,apply_id){
    	var friend_id = customer_id
    	var apply_id = apply_id
    	customer_id = localStorage.customer_id
    	var me=this
    	var key ={}
    	key.friend_id=friend_id
    	key.customer_id=customer_id
    	key.apply_id=apply_id
    	this.$post("/friend/agree",key)
    	.then(res=>{
    		if(res.data.error==0){
          	this.$notify({title: '成功',message: "成功添加好友",duration: 3000})
          } else {
          	this.$notify({title: '失败',message: res.data.message,duration: 3000})
          }
    	}).catch(function(err){
		     console.log(err)
		    });
    	this.handleAll()
    },
    handleRejectApply:function(apply_id){
    	var me=this
    	var key ={}
    	key.apply_id=apply_id
    	this.$post("/friend/reject",key)
    	.then(res=>{
    		if(res.data.error==0){
          	this.$notify({title: '成功',message: "成功拒绝申请",duration: 3000})
          } else {
          	this.$notify({title: '失败',message: res.data.message,duration: 3000})
          }
    	}).catch(function(err){
		     console.log(err)
		    });
    	this.handleAll()
    },
    handleAll:function(){
    	var customer_id = localStorage.customer_id
    	var me = this
    	var key = {}
    	key.customer_id=customer_id
    	this.$post("/friend/all",key)
    	.then(res=>{
    		me.dynamicList=res.data.dynamicList
    		me.friendList=res.data.friendList
    		me.applyList=res.data.applyList
    	}).catch(function(err){
		     console.log(err)
		    })
    },
    handleCreateDynamic:function(){
    	var customer_id=localStorage.customer_id
    	var me=this
    	var key={}
    	if(this.form.content){
    		key.content=this.form.content
    		key.customer_id=customer_id
    	} else {
    		this.$notify({title:"错误",message:"请输入内容",duration:3000})
    		return
    	}

    	this.$post("/dynamic/create",key)
    	.then(res=>{
    		if(res.data.error==0){
    			this.$notify({title: '成功',message: "已发送动态",duration: 3000})
    		} else {
        	this.$notify({title: '失败',message: res.data.message,duration: 3000})
        }
    	}).catch(function(err){
		     console.log(err)
		    })
    	this.handleAll()
    }
	},
	mounted:function(){
		this.handleAll()
	}
}
</script>
<style scoped>
</style>