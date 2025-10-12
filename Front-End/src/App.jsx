import React from 'react';
import Home from './pages/Home/Home';
import Cart from './pages/Cart/Cart';
import PlaceOrder from './pages/PlaceOrder/PlaceOrder';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import SignUp from './pages/SignUp/SignUp';

const App = () => {
    return (
        <div className='app'>
            <Router>
                <Routes>
                    <Route path='/' element={<SignUp />} />
                    <Route path='/home' element={<Home />} />
                    <Route path='/cart' element={<Cart />} />
                    <Route path='/order' element={<PlaceOrder />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;
