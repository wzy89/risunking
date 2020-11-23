<template>
<div class="about-swapper">
    <div class="about-container">
        <div class="about-t1">
            <img :src="currentImgPath" style="width: 100%; height: 100%; display: block;" >
            <el-button class="about-t1-refresh" size="small" icon="el-icon-refresh" circle @click="refreshPic"></el-button>
            <div class="about-t1-text">{{this.currentText}}</div>
        </div>
        <h2 class="about-h2"> 想了解更多？<br> 你需要的都在这里 </h2>
        <div class="about-t2">
            <div class="about-t2-left">
                <h3>关于我</h3>
                <img src="../../assets/self.jpg" style="width: 70%; height: 70%;"> 
                <p>
                    <br>一个集温柔、帅气、风趣、才华<br>于一身的<em>“程序猿”</em>。
                </p>
                <p>
                    本来想简单介绍一下自己的工作<br>经历，但是看到上图（重点头发）<br>额，突然感觉没必要说了。<br>
                </p>
                <p>
                    快乐于生活，就像bug于程序，<br>仔细测一测，总会有的。<br><br><br>
                </p>
            </div>
            <div class="about-t2-line"/>
            <div class="about-t2-middle">
                <h3>联系我</h3>
                <img src="../../assets/weixin.jpg" style="width: 60%; height: 60%;"> 
                <p style="font-size:14px; margin-top: 0;margin-bottom: 30px;">扫码加微信</p>
                <img src="../../assets/QQ.jpg" style="width: 60%; height: 60%;"> 
                <p style="font-size:14px; margin-top: 0;margin-bottom: 20px;">扫码加QQ</p>
            </div>
            <div class="about-t2-line"/>
            <div class="about-t2-right">
                <h3>留言</h3>
                <el-input type="textarea" style="width: 80%;" :rows="8" placeholder="请输入留言" v-model="textarea"></el-input>
                <div style="margin-top:10px">留言会公开显示，请勿在留言内<br>容写下微信号等私人联系方式，<br>谨防诈骗。如果你不想发布公开<br>留言，也可以发送邮件到<br> wzyapdp@163.com 与我联系。</div>
                <el-input v-model="input" style="width: 80%;margin-top:30px" placeholder="请输入您的昵称"></el-input>
                <div>
                    <el-button style="margin-top:25px" type="plain" :disabled="(input==''||textarea=='')" round @click="saveMessage"> 留言 </el-button>
                    <el-button style="margin-top:25px" type="plain" round @click="showMsgList = true"> 查看 </el-button>
                    <el-drawer :visible.sync="showMsgList" :append-to-body="true" direction="rtl" size="35%">
                        <div class="guest-cell-container-warp">
                            <div class="infinite-list-wrapper" style="overflow:auto; height:100%;">
                                <ul v-infinite-scroll="loadMoreGuests" :infinite-scroll-disabled="canNotLoadMore">
                                    <div class="guest-cell" v-for="item in guestList" :key="item.id">
                                        <div style="padding-top:5px; padding-bottom:5px; padding-left:0px; padding-right:10px; text-align:left; color:#333; font-size:15px; line-height:19pt;">{{item.content}}</div>
                                        <div style="width:100%; padding-bottom: 5px; display:flex; flex-direction:row-reverse;">
                                            <div style="width:160px; color:#666;font-size:11px;">{{item.createDate}}</div>
                                            <div style="width:100px; text-align:right; color:#666;font-size:13px;">{{item.nickName}}</div>
                                        </div>
                                        <div style="height:1px; background:#eee; width:100%;"></div>
                                    </div>
                                </ul>
                                <p v-if="loading" style="color:#666;font-size:13px;text-align:center;">加载中...</p>
                                <p v-if="noMore" style="color:#666;font-size:13px;text-align:center;">没有更多数据</p>
                            </div>
                            <div style="width:100%; height:78px;"></div>
                        </div>
                        
                    </el-drawer>
                </div>
            </div>
        </div>
    </div>
</div>
</template>>

<script>
import { Loading } from 'element-ui';
export default {
    data() {
        return {
            dailyWords: [],
            guestList:[],
            currentImgPath:'',
            currentText:'',
            textarea: '',
            input: '',
            showMsgList:false,
            guestSize: 10,
            guestPage: 1,
            guestTotal:0,
            loading: false
        };
    },
    created() {
        this.getDailyWords();
        this.getGuestList();
    },
    computed: {
      noMore () {
        return this.guestList.length >= this.guestTotal;
      },
      canNotLoadMore () {
        return this.loading || this.noMore;
      }
    },
    methods: {
        /** 获取数据 */
        getDailyWords() {
            this.$jsonPost('/web/resource/dailyWord/list',{})
            .then((response)=>{
                this.dailyWords = response.result;
                this.currentImgPath = this.$store.state.baseUrl+this.$store.state.downloadUrl+"?fileName="+this.dailyWords[0].src+'&tsp='+this.$utils.getTsp();
                this.currentText = this.dailyWords[0].text;
                this.dailyWords.splice(0,1);
            });
        },
        /** 刷新图片 */
        refreshPic(){
            if(this.dailyWords.length > 0){
                this.currentImgPath = this.$store.state.baseUrl+this.$store.state.downloadUrl+"?fileName="+this.dailyWords[0].src+'&tsp='+this.$utils.getTsp();
                this.currentText = this.dailyWords[0].text;
                this.dailyWords.splice(0,1);
            }else{
                this.getDailyWords();
            }
        },
        /** 保存留言 */
        saveMessage(){
            let params = {
                "expand":"",
                "content":this.textarea,
                "nickName":this.input,
                "email":""
            }
            this.$jsonPost('/web/resource/guests/add',params)
            .then((response)=>{
                this.$message({
                    message: '留言成功，但您的留言需要作者审核。',
                    type: 'success'
                });
                this.textarea = '';
                this.input = '';
            });
        },
        /** 展示留言列表 第一页 */
        getGuestList(){
            this.loading = true;
            this.guestList = [];
            this.guestSize = 10;
            this.guestPage = 1;
            let params = {
                "page":this.guestPage,
                "size":this.guestSize
            }
            this.$jsonPost('/web/resource/guests/list',params)
            .then((response)=>{
                this.guestList = this.guestList.concat(response.result);
                this.guestTotal = response.count;
                this.loading = false;
            });
        },
        /** 展示留言列表 更多*/
        loadMoreGuests(){
            this.loading = true;
            this.guestPage ++;
            let params = {
                "page":this.guestPage,
                "size":2
            }
            this.$jsonPost('/web/resource/guests/list',params)
            .then((response)=>{
                this.guestList = this.guestList.concat(response.result);
                this.guestTotal = response.count;
                this.loading = false;
            });
        }
    }
}
</script>>


<style scoped>
.about-swapper{
    width: 100%;
    height: 100%;
    overflow-x: hidden;
    overflow-y: scroll;
}
.about-container{
    display: flex;
    flex-direction:column;
    align-items:center;
}
.about-t1{
    width: 75vw;
    height: 40vw;
    position: relative;
}
.about-t1-refresh{
    position: absolute;
    top: 10px;
    right: 10px;
    cursor: pointer;
}
.about-t1-text{
    text-align: center;
    padding: 10px 10px;
    color: black;
    border-color: white;
    position: absolute;
    bottom: 20px;
    left: 0;
    right: 0;
    margin:auto;
    width: 400px;
    font-size: 20px;
    border-radius: 16px;
    background-color: white;
    filter:alpha(Opacity=80);
    -moz-opacity:0.5;
    opacity: 0.5;
    font-family: PMingLiU;
}
.about-h2{
    margin-top: 40px;
}
.about-t2{
    display: flex;
    display: -webkit-flex;
    flex-direction:row;
    justify-content:center;
    margin-top: 10px;
    width: 100vw;
    height: 100%;
}
.about-t2-line{
    width: 0;
    margin-top: 60px;
    margin-bottom: 60px;
    border-left: 1px solid;
    border-color: antiquewhite;
}
.about-t2-left{
    width: 25%;
    height: 80%;
}
.about-t2-middle{
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 25%;
    height: 80%;
}
.about-t2-right{
    width: 25%;
    height: 80%;
}
.guest-cell-container-warp{
    overflow: scroll;
    width: 100%;
    height: 100vh;
}

.list{
    height: 100%;
}
.guest-cell{
    margin-bottom: 2px;
}
</style>