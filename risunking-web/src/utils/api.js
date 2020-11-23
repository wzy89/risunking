import axios from 'axios';
import { Loading } from 'element-ui';

export function jsonPost(url, param) {
    Loading.service({ fullscreen: true });
    return new Promise((resolve, reject) => {
        axios({
            baseURL:this.$store.state.baseUrl,
            method:'post',
            url:url, 
            data:JSON.stringify(param),
            timeout:10000,
            headers:{'Content-type':'application/json;charset=utf-8','tsp':this.$utils.getTsp()}
        }).then(res => {
            console.info(url + "==>>\n",res);
            if (res.status === 200) { 
                let code = res.data.code;
                if(code != undefined && code === 0){
                    resolve(res.data);
                    Loading.service({ fullscreen: true }).close();
                }else{
                    reject(res.data);
                    Loading.service({ fullscreen: true }).close();
                    this.$message.error(res.data.msg + ":" + code);
                }
            } else {
                let data = {code:88,data:{}, msg:res.statusText};
                reject(data);
                Loading.service({ fullscreen: true }).close();
                this.$message.error(data.msg + ":88");
            }
        }).catch(err => {
            let data = {code:99,data:err,msg:"未知错误"};
            reject(data);
            Loading.service({ fullscreen: true }).close();
            this.$message.error(data.msg + ":99");
        })
    })
}
