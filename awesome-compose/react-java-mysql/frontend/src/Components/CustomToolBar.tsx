import React from "react";
import { Toolbar } from "primereact/toolbar";
import { Button } from "primereact/button";
import UsersPicker from "./UsersPicker";

const CustomToolBar: React.FC = () => {
  return (
    <Toolbar className="custom-toolbar">
      <div className="p-toolbar-group-left">
        <UsersPicker />
      </div>
    </Toolbar>
  );
};

export default CustomToolBar;
