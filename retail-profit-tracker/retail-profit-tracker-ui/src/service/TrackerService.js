import axios from 'axios'
const SERVER_PORT_URL = 'http://192.168.1.105:8081'

class TrackerService {

    fetchCategories() {
    	return axios.get(`${SERVER_PORT_URL}/categories`)
    }
    
    fetchProductsForCategory(categoryId) {
    	return axios.get(`${SERVER_PORT_URL}/products/category?categoryId=${categoryId}`);
	}
	
	addTransaction(transaction) {
		console.log(transaction);
		return axios.post(`${SERVER_PORT_URL}/transaction`, transaction);
	}
	
    fetchProducts() {
    	return axios.get(`${SERVER_PORT_URL}/products`);
	}
	
	calculateProfit(asOfDate, categoryId, products, price) {
    	return axios.get(`${SERVER_PORT_URL}/profit?asOfDate=${asOfDate}&categoryId=${categoryId}&productCodes=${products}&purchasePrice=${price}`);
	}
}

export default new TrackerService()