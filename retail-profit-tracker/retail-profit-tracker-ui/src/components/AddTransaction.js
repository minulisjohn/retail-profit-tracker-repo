import React, {Component} from 'react';
import TrackerService from '../service/TrackerService';
import Navbar from './Navbar';

class AddTransaction extends Component{
	constructor(props) {
	
        super(props)
        this.state = {
            categories : [],
            selectedCategoryId : '',
            products : [],
            selectedProduct : '',
            transactionDate : '',
            quantity : '',
            price : '',
            transactionType : '',
            transactionAdded : '',
            successMessage : '',
            profit : '',
            isErrorPresent : '',
            errorMessage : ''
        }
        this.loadCategoryDropdown = this.loadCategoryDropdown.bind(this);
        this.loadProductDropdown = this.loadProductDropdown.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    
    componentDidMount() {
        this.loadCategoryDropdown();
    }
    
    //REST API call to fetch all the categories
    loadCategoryDropdown() {
        TrackerService.fetchCategories()
            .then(res => {
            	this.setState({
            					categories: res.data
			     			 });
                })
            .catch(err => {
            	this.setState({
            					isErrorPresent: 'true',
            					errorMessage: err.message
			     			 });
            });
            
    }
    
    //REST API call to fetch all the products for the passed in category
    loadProductDropdown(categoryId) {
        TrackerService.fetchProductsForCategory(categoryId)
            .then(res => {
            	this.setState({
            					products: res.data
			     			 });
                })
            .catch(err => {
            	this.setState({
            					isErrorPresent: 'true',
            					errorMessage: err.message
			     			 });
            });
    }
    
    //Handling change for each event
    handleChange(event) {
    	const {name, value} = event.target;
		this.setState({[name] : value});
		if(name === 'selectedCategoryId') {
			this.loadProductDropdown(value);
		}
	}
	
	//Handling client side validation and calculating profit
	handleSubmit(event) {
		event.preventDefault();
		
		let date = (new Date(this.state.transactionDate)).getTime();
		if(isNaN(date)) {
			alert('Please enter a valid transaction date');
			return false;
		}
		
		if(this.state.quantity === '' || isNaN(this.state.quantity)) {
			alert('Please enter a valid quantity');
			return false;
		}
		
		if(this.state.price === '' || isNaN(this.state.price)) {
			alert('Please enter a valid price');
			return false;
		}
		
		
	    let transaction = {
			    'product' : this.state.selectedProduct,
			    'transactionDate' : this.state.transactionDate,
			    'quantity' : this.state.quantity,
			    'price' : this.state.price,
			    'transactionType' : this.state.transactionType
			};
	    TrackerService.addTransaction(transaction)
            .then(res => {
            	this.setState({
            					transaction: res.data,
            					successMessage: 'Added successfully'
			     			 });
                })
            .catch(err => {
            	this.setState({
            					isErrorPresent: 'true',
            					errorMessage: err.message
			     			 });
            });
	}
  
	render() {
		let {categories, selectedCategoryId, products, selectedProduct, transactionDate,
		quantity, price, transactionType, transactionAdded, successMessage, profit, isErrorPresent, errorMessage} = this.state;
		return (
		<form onSubmit={this.handleSubmit}>
			<div className="container">
			<br/>
			<Navbar/>
				<h3>Add a Purchase/Sale</h3>
				
				Select Purchase/Sale
		          <label>
		            <input 
		            	type="radio"
		                name="transactionType" 
		                value="Purchase" 
		                checked={transactionType === "Purchase"}
		                onChange={this.handleChange}/>
		            Purchase
		          </label>
		          <label>
		            <input 
		            	type="radio"
		                name="transactionType" 
		                value="Sale" 
		                checked={transactionType === "Sale"}
		                onChange={this.handleChange}/>
		            Sale
		          </label>
		        <br/><br/>
		        
				Select a category
				<select 
					name="selectedCategoryId"
			    	value={selectedCategoryId} 
			        onChange={this.handleChange} 
			    >
			    {categories.map(category => <option value = {category.id} key = {category.id}>{category.name}
			    </option>
			    )}</select>
			    <br/><br/>
			    
			    Select a product
				<select
					name="selectedProduct" 
			    	value={selectedProduct} 
			        onChange={this.handleChange} 
			    >
			    <option>Select</option>
			    {products.map(product => <option value = {product} key = {product.productCode}>{product.name}
			    </option>
			    )}</select>
			    <br/><br/>
			    
			    Enter the transaction date
			    <input type="text" name="transactionDate" value={transactionDate} placeholder="Enter in YYYY-MM-DD(2020-10-31) format" onChange={this.handleChange} />
			    <br/><br/>
			    
			    Enter the quantity
			    <input type="text" name="quantity" value={quantity} onChange={this.handleChange} />
			    <br/><br/>
			    
			    Enter the price
			    <input type="text" name="price" value={price} onChange={this.handleChange} />
			    <br/><br/>
			     
		        <button>Add</button>
		    	{isErrorPresent === 'false' &&(<div><b><font color="green">{successMessage}{profit}</font></b></div>)}   
		    	{isErrorPresent === 'true' &&(<div><b><font color="red">{errorMessage}</font></b></div>)}
		    
		    </div>
		    </form>
		    
		)
	};
}

export default AddTransaction;