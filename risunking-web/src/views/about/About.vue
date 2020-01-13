<template>
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
                <el-button style="margin-top:25px" type="info" :disabled="(input==''||textarea=='')" round @click="saveMessage"> 完成 </el-button>
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
            currentImgPath:'',
            currentText:'',
            textarea: '',
            input: ''
        };
    },
    created() {
        this.getDailyWords();
    },
    methods: {
        /** 获取数据 */
        getDailyWords() {
            Loading.service({ fullscreen: true });
            var dailyWord1 = {
                text:'不要以为无所事事和试图忘却会给你们带来安慰。要像往常一样继续工作，因为工作是使人愉快的安慰。',
                src:'http://www.risunking.com/web/storage/downloadFile?fileName=9ab49723-5b79-409b-a708-d59f41f873f2.png'
            }
            this.dailyWords.push(dailyWord1);
            var dailyWord2 = {
                text:'几处早莺争暖树，谁家新燕啄春泥。',
                src:'http://www.risunking.com/web/storage/downloadFile?fileName=82ebdda0-0da7-449a-94ff-382496375fdc.png'
            }
            this.dailyWords.push(dailyWord2);
            var dailyWord3 = {
                text:'哲学家们只是用不同的方式解释世界，而问题在于改变世界。',
                src:'http://www.risunking.com/web/storage/downloadFile?fileName=8eca1c38-5488-464f-9514-b2e0ec606862.png'
            }
            this.dailyWords.push(dailyWord3);
            
            this.currentImgPath = this.dailyWords[0].src+'&tsp='+this.$utils.getTsp();
            this.currentText = this.dailyWords[0].text;
            this.dailyWords.splice(0,1);
            Loading.service({ fullscreen: true }).close();
        },
        /** 刷新图片 */
        refreshPic(){
            if(this.dailyWords.length > 0){
                this.currentImgPath = this.dailyWords[0].src+'&tsp='+this.$utils.getTsp();
                this.currentText = this.dailyWords[0].text;
                this.dailyWords.splice(0,1);
            }else{
                this.getDailyWords();
            }
        },
        /** 保存留言 */
        saveMessage(){
            console.log('留言：'+this.textarea+";昵称："+this.input);
        }
    }
}
</script>>


<style scoped>
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
</style>>