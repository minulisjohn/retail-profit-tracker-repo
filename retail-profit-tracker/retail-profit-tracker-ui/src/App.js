import React from 'react';
import './App.css';
import { Route, Switch } from 'react-router-dom';
import AddTransaction from './components/AddTransaction';
import CalculateProfit from './components/CalculateProfit';
import Home from './components/Home';

function App() {
    return (
        <main>
            <Switch>
                <Route path="/" component={Home} exact />
                <Route path="/addTransaction" component={AddTransaction}  />
                <Route path="/calculateProfit" component={CalculateProfit}  />
            </Switch>
        </main>
    )
}

export default App;
