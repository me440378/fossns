<template>
	<div>
		<el-card style="width: 80%;margin: auto;">
		<div slot="header"><span>会话</span></div>
		<div>
			<el-row v-for="item in chatDetailList" style="margin: 5px;">
				<el-col :span="24" :style="{'text-align': handlePosition(item.sender_id)}">
					<el-row>
						<el-col :span="24"><span style="color: #999">{{item.sender_nickname}} {{item.position}}</span></el-col>
					</el-row>
					<el-row>
						<el-col :span="24"><span>{{item.content}}</span></el-col>
					</el-row>
				</el-col>
			</el-row>
		</div>
		</el-card>
		<el-card style="width: 80%;margin: auto;">
			<div>
				<el-input type="textarea" :rows="3" v-model="form.content"></el-input>
				<div style="margin: 10px;text-align: right;">
					<el-button type="error" @click="handleBack">返回</el-button>
					<el-button type="primary" @click="handleSubmit">发送</el-button>
				</div>
			</div>
		</el-card>
	</div>
</template>
<script>
export default{
	data(){
		return{
			form:{
				content:""
			},
			chatDetailList:[
				// {sender_id:1,sender_nickname:"A君",receiver_id:2,receiver_nickname:"B君",content:"你好",created_at:"2018-06-18 13:36"},
				// {sender_id:1,sender_nickname:"A君",receiver_id:2,receiver_nickname:"B君",content:"你好",created_at:"2018-06-18 13:36"},
				// {sender_id:2,sender_nickname:"B君",receiver_id:1,receiver_nickname:"A君",content:"你好",created_at:"2018-06-18 13:36"},
				// {sender_id:1,sender_nickname:"A君",receiver_id:2,receiver_nickname:"B君",content:"你好",created_at:"2018-06-18 13:36"},
				// {sender_id:1,sender_nickname:"A君",receiver_id:2,receiver_nickname:"B君",content:"你好",created_at:"2018-06-18 13:36"},
			]
		}
	},
	methods:{
		handleAll:function(){
			var customer_id = localStorage.customer_id
			var friend_id = this.$route.query.receiver_id
			var key={}
			var me=this
			key.customer_id=customer_id
			key.friend_id=friend_id
			this.$post("/chat/all",key)
			.then(res=>{
				me.chatDetailList=res.data.chatDetailList
			}).catch(function(err){
		     console.log(err)
		    });
		},
		handlePosition(sender_id){
			var customer_id = localStorage.customer_id
			if(customer_id == sender_id){
				return "right"
			} else return "left"
		},
		handleBack:function(){
			this.$router.back();
		},
		handleSubmit:function(){
			var sender_id = localStorage.customer_id
			var receiver_id = this.$route.query.receiver_id
			var key={}
			var me=this
			if(this.form.content){
				key.content=this.form.content
				key.type=1
			} else {
				this.$notify({title: '错误',message: "请输入内容",duration: 3000})
				return;
			}
			key.sender_id=sender_id
			key.receiver_id=receiver_id
			this.$post("/chat/create",key)
			.then(res=>{
				if(res.data.error==0){
					this.$notify({title: '成功',message: "发送成功",duration: 3000})
					me.form.content=""
				} else{
					this.$notify({title: '失败',message: res.data.message,duration: 3000})
				}
			}).catch(function(err){
		     console.log(err)
		    });
			this.handleAll();
		}
	},mounted:function(){
		this.handleAll();
	}
}
</script>
<style scoped>
</style>