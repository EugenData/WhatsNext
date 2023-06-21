import React, { useEffect, useState } from "react";
import { Button } from "primereact/button";
import { TabMenu } from "primereact/tabmenu";
import { MenuItem } from "primereact/menuitem";
import AllMovies from "./AllMovies";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Dialog } from "primereact/dialog";
import axios from "axios";
import { Card } from "primereact/card";
import { InputTextarea } from "primereact/inputtextarea";

interface Movie {
  movieId: string;
  title: string;
  genre: string;
  releaseDate: number;
  year: number;
  reviews: [any];
  sequels: [any];
  awards: [any];
  actors: [any];
}
interface Review {
  dateCreated: Date;
  reviewText: string;
  review_id: string;
}

export default function MainBlock() {
  const [activeIndex, setActiveIndex] = useState<number>(3);
  const [selected_movie, setSelected_movie] = useState<Movie>();
  const [visible, setVisible] = useState(false);
  const [value, setValue] = useState("");
  const items: MenuItem[] = [
    { label: "Home", icon: "pi pi-fw pi-home" },
    { label: "Calendar", icon: "pi pi-fw pi-calendar" },
  ];
  const footerContent = (
    <div>
      <Button
        label="No"
        icon="pi pi-times"
        onClick={() => setVisible(false)}
        className="p-button-text"
      />
      <Button
        label="Yes"
        icon="pi pi-check"
        onClick={() => {
          postReviewForMovie(value, selected_movie?.movieId);
          setVisible(false);
        }}
        autoFocus
      />
    </div>
  );
  const [movies, setMovies] = useState<Movie[]>([]);

  useEffect(() => {
    fetchMovies();
  }, []);

  const postReviewForMovie = async (reviewText: string, movieId?: string) => {
    try {
      const res = await axios.post("http://localhost:8080/review/create", {
        reviewText,
        userId: "1",
        movieId,
      });
      console.log(res.data);
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };
  const fetchMovies = async () => {
    try {
      const response = await axios.get<Movie[]>("http://localhost:8080/movies");
      setMovies(response.data);
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };
  function fetchMovieById(param: Movie) {
    console.log(param);
    setSelected_movie(param);
  }

  const checkForReviewToMovie = async () => {
    try {
      if (!selected_movie) {
        return;
      }
      const response = await axios.get<Review>("http://localhost:8080/review", {
        headers: {
          "user-id": "1",
          "movie-id": selected_movie.movieId,
        },
      });
      console.log(response.data);
      if (response.data.review_id === null) {
        setVisible(true);
      }
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };
  return (
    <div>
      <Dialog
        header="Header"
        visible={visible}
        style={{ width: "50vw" }}
        onHide={() => setVisible(false)}
        footer={footerContent}
      >
        <InputTextarea
          value={value}
          onChange={(e) => setValue(e.target.value)}
          rows={5}
          cols={30}
        />
      </Dialog>
      <TabMenu
        className="card custom-toolbar"
        model={items}
        activeIndex={activeIndex}
        onTabChange={(e) => setActiveIndex(e.index)}
      />
      {activeIndex === 0 && (
        <div>
          <div>
            <div className="scrollable-table">
              <h3>Movie List</h3>
              <div className="table-container">
                <DataTable value={movies}>
                  <Column header="Title" field="title" />
                  <Column header="Genre" field="genre" />
                  <Column header="Year" field="releaseDate" />
                  <Column
                    header="Actions"
                    body={(rowData: Movie) => (
                      <button onClick={(e) => fetchMovieById(rowData)}>
                        Edit
                      </button>
                    )}
                  />
                </DataTable>
              </div>
            </div>
          </div>
          {selected_movie && (
            <div className="card ">
              <Card title={selected_movie.title}>
                <p>Genre:{selected_movie.genre} </p>

                <p>Year:{selected_movie.year}</p>
                <Button
                  label="Write review"
                  onClick={async () => {
                    await checkForReviewToMovie();
                  }}
                />
              </Card>
            </div>
          )}
        </div>
      )}
    </div>
  );
}
