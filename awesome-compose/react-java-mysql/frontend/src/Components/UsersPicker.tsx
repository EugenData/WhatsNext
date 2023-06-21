import React, { useEffect, useState } from "react";
import axios from "axios";
import { Dropdown } from "primereact/dropdown";

interface User {
  id: number;
  userName: string;
  password: string;
  email: string;
  watchlists: [any];
  reviews: [any];
}

const UsersPicker: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get<User[]>(
          "http://localhost:8080/api/users"
        );
        setUsers(response.data);
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    };

    fetchUsers();
  }, []);

  console.log(users);

  return (
    <Dropdown
      options={users}
      optionLabel="userName"
      placeholder="Select a user"
    />
  );
};

export default UsersPicker;
