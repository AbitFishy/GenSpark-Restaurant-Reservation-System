import React from 'react';
import { Outlet } from 'react-router';
import Navbar from '../Navbar';


const WithNav = ({handleLogout, isRegistered}) => {
  return (
    <>
    <Navbar handleLogout={handleLogout} isRegistered={isRegistered}/>
    <Outlet />
    </>
  )
}

export default WithNav