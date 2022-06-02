import React from 'react';
import { Outlet } from 'react-router';
import NavLogin from '../NavLogin';


const WithOut = () => {
  return (
    <>
    <NavLogin />
    <Outlet />
    </>
  )
}

export default WithOut