import axios from "axios";

axios.defaults.withCredentials = true;
class DataService{

    async getData(url){
        return await axios.get(url)
    }
    async postData(url){
        await axios.post(url);
    }

    async postWithBody(url, data) {
        await axios.post(url,data);
    }
    async sendDelete(url){
        await axios.delete(url)
    }

    async sendPut(url){
        await axios.put(url)
    }

    async getProductsByCategory(url){
        return await axios.get(url);
    }

}
export default new DataService();