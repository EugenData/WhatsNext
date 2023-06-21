import React, { useEffect, useState } from "react";
import { Card } from "primereact/card";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import axios from "axios";

interface Movie {
  movie_id: number;
  title: string;
  genre: string;
  releaseDate: Date;
  year: number;
  reviews: [any];
  sequels: [any];
  awards: [any];
  actors: [any];
}

const AllMovies: React.FC = () => {
  const [movies, setMovies] = useState<Movie[]>([]);

  useEffect(() => {
    fetchMovies();
  }, []);

  const fetchMovies = async () => {
    try {
      const response = await axios.get<Movie[]>("http://localhost:8080/movies");
      setMovies(response.data);
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };

  return (
    <div className="scrollable-table">
      <h3>Movie List</h3>
      <div className="table-container">
        <DataTable value={movies}>
          <Column header="Title" field="title" />
          <Column header="Genre" field="genre" />
          <Column header="Year" field="releaseDate" />
          <Column
            header="Actions"
            body={(rowData: Movie) => <button>Edit</button>}
          />
        </DataTable>
      </div>
    </div>
  );
};

export default AllMovies;
