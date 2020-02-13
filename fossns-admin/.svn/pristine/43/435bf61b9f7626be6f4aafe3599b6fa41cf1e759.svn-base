<template>
<div>
<!-- <div slot="header" class="clearfix" style="height: 30px;"> -->
	<!-- <span style="font-family:Microsoft YaHei;font-size: 27px;display:width: 200px;">轮播图管理</span> -->
  <!--   <el-button type="primary" @click="handleAdd()">添加轮播图</el-button>
</div> -->
	<el-row>
  	<el-col :span="8" v-for="(item, index) in bannerList">
    	<el-card style="margin: 20px;">
      	<img :src="item.image" style="height: 300px;">
      	<div>
        	<span v-if="item.is_public==1" style="display: block;height: 20px;">{{index+1}}  {{item.title}}  公开</span>
        	<span v-else style="display: block;height: 20px;">{{index+1}}  {{item.title}}  不公开</span>
        	<div>
          	<div style="text-align: right;" >
            	<img src="@/assets/icon_edit.png" style="margin-right:10px;height: 26px;width: 26px;" @click="handleShowEdit(item)">
              <img src="@/assets/icon_delete.png" style="margin-right:10px;height: 26px;width: 26px;" @click="handleDelete(item.id)">
          	</div>
        	</div>
      	</div>
    	</el-card>
    	<el-dialog :title="form.titleHeader" :visible.sync="form.visible" width="40%">
			  <el-form ref="form" :model="form.data" label-width="110px">
			    <el-form-item label="图片标题：">
			      <el-input placeholder="请输入图片标题" v-model="form.data.title" ></el-input>
			    </el-form-item>
			    <el-form-item label="图片地址：">
			       <el-input placeholder="请输入图片地址：格式http://xxx" v-model="form.data.image" ></el-input>
			    </el-form-item>
			     <el-form-item label="跳转链接：">
			       <el-input placeholder="请输入跳转链接：格式http://xxx" v-model="form.data.url" ></el-input>
			    </el-form-item>
			    <el-form-item label="是否公开：">
			      <el-col :span="17">
              <el-select v-model="form.data.is_public" placeholder="请选择审核状态">
                <el-option label="公开" value="1"></el-option>
                <el-option label="不公开" value="0"></el-option>
              </el-select>
            </el-col>
			    </el-form-item>
        </el-form>
			  <div slot="footer">
			    <el-button type="primary" @click="handleConfirm">确 认</el-button>
			    <el-button  @click="form.visible = false">取 消</el-button>
			  </div>
		  </el-dialog>
  	</el-col>
	</el-row>
    <el-button type="primary" @click="handleShowAdd()">添加轮播图</el-button>
</div>
</template>
<script>
export default{
	data(){
		return{
				bannerList:[
					// {id:"1",title:"天使",image:"http://i1.bvimg.com/650997/5557d070c3a7a86b.jpg",is_public:1,url:"/1"},
					// {id:"2",title:"兔子爱好者",image:"http://i1.bvimg.com/650997/1e3a4ad46751f0f1.jpg",is_public:0,url:"/2"},
					// {id:"3",title:"天空的想念",image:"http://i1.bvimg.com/650997/9f8cd08adca46aa1.png",is_public:1,url:"/3"},
					// {id:"5",title:"天空的想念",image:"http://i1.bvimg.com/650997/9f8cd08adca46aa1.png",is_public:1,url:"/4"},
				],
		    form:{
			        visible:false,
			        titleHeader: '',
			        disabled: false,
			        data: 
			          {
			          id:'',
			          title:'',
			          image:'',
			          is_public:'',
			          url:'',
                kind:''
			         }
			      },
				
			}
		
	},
	methods:{
    handleAll:function(){
      var key={}
      var me=this
      this.$post("/admin/banner/all",key)
      .then(res=>{
        me.bannerList=res.data.bannerList
      })
      .catch(function(err){
        console.log(err)
      });
    },
    handleConfirm:function(){
      if(this.form.data.kind==0){
        this.handleEdit()
      } else this.handleAdd()
    },
    handleShowEdit:function(item){
    	this.form.titleHeader = '修改轮播图内容';
    	this.form.disabled =false;
    	this.form.data.id = item.id;
    	this.form.data.title = item.title;
    	this.form.data.image = item.image;
    	this.form.data.is_public = (item.is_public==true) ? 1 : 0;
    	this.form.data.url = item.url;
      this.form.data.kind=0;
      this.form.visible = true;
    },
    handleEdit:function(){
    	var key={}
      key.id=this.form.data.id
      key.title=this.form.data.title
      key.image=this.form.data.image
      key.is_public=this.form.data.is_public
      key.url=this.form.data.url
      var me=this
      this.$post("/admin/banner/update",key)
      .then(res=>{
        if(res.data.error==0){
            this.$notify({title: '成功',message: "修改成功",duration: 3000})
          } else {
            this.$notify({title: '失败',message: res.data.message,duration: 3000})
          }
      })
      .catch(function(err){
        console.log(err)
      });
      this.form.visible = false
      this.handleAll()
    },
    handleShowAdd:function(){
    	this.form.titleHeader = '添加轮播图内容';
    	this.form.disabled =false;
    	this.form.data.id = '';
    	this.form.data.title = '';
    	this.form.data.image = '';
    	this.form.data.is_public = '';
    	this.form.data.url = '';
      this.form.data.kind=1;
      this.form.visible = true;
    },
    handleAdd:function(){
      var key={}
      key.title=this.form.data.title
      key.image=this.form.data.image
      key.is_public=this.form.data.is_public
      key.url=this.form.data.url
      var me=this
      this.$post("/admin/banner/create",key)
      .then(res=>{
        if(res.data.error==0){
            this.$notify({title: '成功',message: "添加成功",duration: 3000})
          } else {
            this.$notify({title: '失败',message: res.data.message,duration: 3000})
          }
      })
      .catch(function(err){
        console.log(err)
      });
      this.form.visible = false
      this.handleAll()
    },
    handleDelete:function(id){
    	var key={}
      key.id=id
      var me=this
      this.$post("/admin/banner/delete",key)
      .then(res=>{
        if(res.data.error==0){
            this.$notify({title: '成功',message: "删除成功",duration: 3000})
          } else {
            this.$notify({title: '失败',message: res.data.message,duration: 3000})
          }
      })
      .catch(function(err){
        console.log(err)
      });
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