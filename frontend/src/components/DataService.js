import axios from "axios";

class DataService{
    async getData(url){
        return await axios.get(url)
    }
    async postData(url){
        await axios.post(url);
    }
    async sendDelete(url){
        await axios.delete(url)
    }

    async sendPut(url){
        await axios.put(url)
    }

}
export default new DataService();