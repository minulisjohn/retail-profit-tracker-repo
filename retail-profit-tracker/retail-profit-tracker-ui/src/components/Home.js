import React from 'react';
import Navbar from './Navbar';

function App() {
    return (
    	<div>
	        <Navbar/>
	        <br/>
	        <div>
		        Welcome...
		        <br/><br/>
		        To add a purchase or sale, click on the link 'Add Transaction'
		        <br/>
		        To calculate Profit, click on the link 'Calculate profit'
		    </div>
	    </div>
    )
}

export default App;
