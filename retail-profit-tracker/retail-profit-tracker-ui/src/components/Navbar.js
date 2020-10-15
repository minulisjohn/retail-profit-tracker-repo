import React from 'react';
import {Link} from 'react-router-dom';

function Navbar() {
  return (
    <div>
      <Link to="/">Home </Link>&nbsp;&nbsp;
      <Link to="/addTransaction">Add Transaction </Link>&nbsp;&nbsp;
      <Link to="/calculateProfit">Calculate Profit </Link>
    </div>
  );
};

export default Navbar;