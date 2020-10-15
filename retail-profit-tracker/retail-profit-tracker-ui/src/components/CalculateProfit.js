import React, {Component} from 'react';
import TrackerService from '../service/TrackerService';
import Navbar from './Navbar';

class CalculateProfit extends Component{
	constructor(props) {
	
        super(props)
        this.state = {
            productCategorySelection : '',
            categories : [],
            selectedCategoryId : '',
            products : [],
            selectedProducts : '',
            asOfDate : '',
            price : '',
            profit : '', 
            successMessage : '',
            isErrorPresent : '',
            errorMessage : ''
        }
        this.loadCategoryDropdown = this.loadCategoryDropdown.bind(this);
        this.loadProductDropdown = this.loadProductDropdown.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    
    componentDidMount() {
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
    
    //REST API call to fetch all the products
    loadProductDropdown() {
        TrackerService.fetchProducts()
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
		if(name === 'productCategorySelection') {
			if(value === 'Category') {
				this.loadCategoryDropdown();
			} else if(value === 'Product') {
				this.loadProductDropdown();
			}
		}
	}
	
	//Handling client side validation and adding transaction to the database
	handleSubmit(event) {
		event.preventDefault();
		
	    if(this.state.asOfDate !== '') {
	    	let date = (new Date(this.state.asOfDate)).getTime();
			if(isNaN(date)) {
				alert('Please enter a valid transaction date');
				return false;
			}
	    }
		
		if(this.state.price !== '' && isNaN(this.state.price)) {
			alert('Please enter a valid price');
			return false;
		}
		
	    TrackerService.calculateProfit(this.state.asOfDate, this.state.selectedCategoryId, this.state.selectedProducts, this.state.price)
            .then(res => {
            	this.setState({
            					profit: res.data,
            					successMessage: 'Profit is ',
            					isErrorPresent: 'false',
			     			 });
                })
            .catch(err => {
            	this.setState({
            					isErrorPresent: 'true',
            					errorMessage: 'System encountered error. Please contact Admin'
			     			 });
            });
	}
  
	render() {
		let {productCategorySelection, categories, selectedCategoryId, products, selectedProducts, asOfDate,
		price, profit, successMessage, isErrorPresent, errorMessage} = this.state;
		return (
		<form onSubmit={this.handleSubmit}>
			<div className="container">
			<br/>
			<Navbar/>
				<h3>Calculate Profit</h3>
				
				Do you want to add Product/Category
		          <label>
		            <input 
		            	type="radio"
		                name="productCategorySelection" 
		                value="Product" 
		                checked={productCategorySelection === "Product"}
		                onChange={this.handleChange}/>
		            Product
		          </label>
		          <label>
		            <input 
		            	type="radio"
		                name="productCategorySelection" 
		                value="Category" 
		                checked={productCategorySelection === "Category"}
		                onChange={this.handleChange}/>
		            Category
		          </label>
		        <br/><br/>
		        
		        {productCategorySelection === "Category" && (
			        <div>
						Select a category
						<select 
							name="selectedCategoryId"
					    	value={selectedCategoryId} 
					        onChange={this.handleChange} 
					    >
					    {categories.map(category => <option value = {category.id} key = {category.id}>{category.name}
					    </option>
					    )}</select>
				    </div>)
			    }
			    
			    {productCategorySelection === "Product" && (
			        <div>
					    Select a product
						<select
							name="selectedProducts" 
					    	value={selectedProducts} 
					        onChange={this.handleChange} 
					    >
					    <option>Select</option>
					    {products.map(product => <option value = {product.id} key = {product.productCode}>{product.name}
					    </option>
					    )}</select>
				    </div>)
			    }
			    <br/>
			    Enter the As of date
			    <input type="text" name="asOfDate" value={asOfDate} placeholder="Enter in YYYY-MM-DD(2020-10-31) format" onChange={this.handleChange} />
			    If not entered, today's date will be considered<br/><br/>
			    
			    Enter the purchase price
			    <input type="text" name="price" value={price} onChange={this.handleChange} />
			    <br/><br/>
			     
		        <button>Calculate Profit</button>
		        <br/><br/>
		    {isErrorPresent === 'false' &&(<div><b><font color="green">{successMessage}{profit}</font></b></div>)}   
		    {isErrorPresent === 'true' &&(<div><b><font color="red">{errorMessage}{profit}</font></b></div>)}
		    </div>
		    </form>
		    
		)
	};
}

export default CalculateProfit;