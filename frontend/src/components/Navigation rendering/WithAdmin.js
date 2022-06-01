import React from 'react'
import {Outlet} from 'react-router-dom'
import NavbarAdmin from '../Admin/NavbarAdmin'

const WithAdmin = ({handleLogout}) => {
  return (
    <>
    <NavbarAdmin handleLogout={handleLogout}/>
    <Outlet />
    </>
  )
}

export default WithAdmin