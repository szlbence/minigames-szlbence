import axios from "axios";

class DataService{
    async getData(url){
        return await axios.get(url)
    }
    async getProductBoxId(url, name){
        const productBoxId = await axios.get(`${url}/${name}`);
        const newId = productBoxId.data.replace(`"`, '');
        const finalId = newId.slice(0, newId.length);
        return await finalId;
    }
    async postData(url){
        axios.post(url);
    }
    async sendDelete(url){
        axios.delete(url)
    }

}
export default new DataService();