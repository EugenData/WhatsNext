import React, { useEffect, useRef, useState } from "react";
import { Button } from "primereact/button";
import { TabMenu } from "primereact/tabmenu";
import { MenuItem } from "primereact/menuitem";
import AllMovies from "./AllMovies";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Dialog } from "primereact/dialog";
import axios from "axios";
import { Card } from "primereact/card";
import { InputTextarea } from "primereact/inputtextarea";
import { Dropdown } from "primereact/dropdown";
import { ToggleButton, ToggleButtonChangeEvent } from "primereact/togglebutton";
import { Toast } from "primereact/toast";
import { Chip } from 'primereact/chip';

interface Movie {
  movieId: string;
  title: string;
  genre: string;
  releaseDate: number;
  reviews: [Review];
  sequels: [Movie];
  awards: [Award];
  actors: [any];
}
interface Review {
  dateCreated: Date;
  reviewText: string;
  review_id: string;
}
interface User {
  id: string;
  userName: string;
  watchlists: [any];
  reviews: [any];
}
interface Award {
  award_id: string;
  award_name: string;
  dateAwarded: Date;
  movie: Movie;
}

export default function MainBlock() {
  const [activeIndex, setActiveIndex] = useState<number>(0);
  const [selected_movie, setSelected_movie] = useState<Movie>();
  const [selectedUser, setSelectedUser] = useState<User>();
  const [visible, setVisible] = useState(false);
  const [value, setValue] = useState("");
  const [users, setUsers] = useState<User[]>([]);
  const [movies, setMovies] = useState<Movie[]>([]);
  const [reportMovie, setreportMovie] = useState<Movie>();
  const toast = useRef<Toast>(null);
  const toastError = useRef<Toast>(null);
  const [checked, setChecked] = useState(false);

  const items: MenuItem[] = [
    { label: "Main", icon: "pi pi-fw pi-home" },
    { label: "Report", icon: "pi pi-fw pi-calendar" },
  ];

  useEffect(() => {
    fetchUsers();
    fetchMovies();
  }, []);

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
          postReviewForMovie(value, selected_movie?.movieId, checked);
          setVisible(false);
        }}
        autoFocus
      />
    </div>
  );

  const showWarn = () => {
    toast.current?.show({
      severity: "warn",
      summary: "Server Warning",
      detail: "You have already created review for this movie !",
      life: 3000,
    });
  };
  const showError = () => {
    toastError.current?.show({
      severity: "error",
      summary: "Server ",
      detail: "DEMO : To write a review, you have to select a user",
      life: 3000,
    });
  };

  const getMovieById = async () => {
    try {
      const response = await axios.get<Movie>(
        `http://localhost:8080/movies/${selected_movie?.movieId}`
      );

      if (response.data) {
        setSelected_movie(response.data);
      }
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };

  const postReviewForMovie = async (
    reviewText: string,
    movieId?: string,
    typeOfReview?: boolean
  ) => {
    try {
      if (!selectedUser) {
        return;
      }
      const res = await axios.post("http://localhost:8080/review/create", {
        reviewText,
        userId: selectedUser.id,
        movieId,
        typeOfReview,
      });

      if (res) {
        getMovieById();
      }
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };

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

  const fetchMovies = async () => {
    try {
      const response = await axios.get<Movie[]>("http://localhost:8080/movies");
      setMovies(response.data);
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };

  const fetchMovieById = async (param: Movie) => {
    try {
      const response = await axios.get<Movie>(
        `http://localhost:8080/movies/${param.movieId}`
      );

      if (response) {
        setSelected_movie(response.data);
      }
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };

  const callPopulateDb = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/populate/sqldatabase"
      );
      if (response) {
        fetchUsers();
        fetchMovies();
      }
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };

  const checkForReviewToMovie = async () => {
    if (!selectedUser) {
      showError();
    }
    try {
      if (!selected_movie || !selectedUser) {
        return;
      }
      const response = await axios.get<Review>("http://localhost:8080/review", {
        headers: {
          "user-id": selectedUser.id,
          "movie-id": selected_movie.movieId,
        },
      });
      console.log(response.data);
      if (response.data.review_id === null) {
        setVisible(true);
      } else {
        showWarn();
      }
    } catch (error) {
      console.log("Error fetching movies:", error);
    }
  };
  const callReport = async () => {
    try {
      const response = await axios.get<Movie>(
        "http://localhost:8080/movies/report"
      );
      setreportMovie(response.data);
    } catch (error) {
      console.error("Error fetching Movie:", error);
    }
  };

  return (
    <div className="container">
      <Toast ref={toast} />
      <Toast ref={toastError} />
      <div className="row ">
        <div className="col-md-6">
          <div>
            <Dialog
              header="Create new review"
              visible={visible}
              style={{ width: "50vw" }}
              onHide={() => setVisible(false)}
              footer={footerContent}
            >
              <InputTextarea
                value={value}
                style={{ width: "100%" }}
                onChange={(e) => setValue(e.target.value)}
              />
              <ToggleButton
                onLabel="positive"
                offLabel="negative"
                checked={checked}
                onChange={(e: ToggleButtonChangeEvent) => setChecked(e.value)}
                className="w-8rem"
              />
            </Dialog>
            <TabMenu
              className="custom-toolbar"
              model={items}
              activeIndex={activeIndex}
              onTabChange={(e) => {
                setActiveIndex(e.index);
                callReport();
              }}
            />
            {activeIndex === 0 && (
              <>
                <div className="custom-card">
                  <div className="scrollable-table">
                    <DataTable
                      value={movies}
                      header={
                        <>
                          <div>
                            <Button
                              label="Populate sqldb"
                              onClick={(e) => callPopulateDb()}
                            />
                            <div className="user-dropdown">
                              <span className="p-float-label">
                                <Dropdown
                                  inputId="dd-user"
                                  options={users}
                                  optionLabel="userName"
                                  placeholder="Select a user"
                                  onChange={(e) => setSelectedUser(e.value)}
                                  value={selectedUser}
                                />
                                <label htmlFor="dd-user">Selected user</label>
                              </span>
                            </div>
                          </div>
                          <h3>All Movies</h3>
                        </>
                      }
                    >
                      <Column header="Title" field="title" />
                      <Column header="Genre" field="genre" />
                      <Column header="Year" field="releaseDate" />
                      <Column
                        header="Actions"
                        body={(rowData: Movie) => (
                          <Button
                            title="Select"
                            label="Select"
                            onClick={async (e) => await fetchMovieById(rowData)}
                            rounded
                            severity="secondary"
                          />
                        )}
                      />
                    </DataTable>
                  </div>
                  <div className="col-2">
                    {selected_movie && (
                      <div className="selected-movie-card ">
                        <Card title={<h2>{selected_movie.title}</h2>}>
                          <h3>Genre: {selected_movie.genre} </h3>

                          <h3>Year: {selected_movie.releaseDate}</h3>
                          <Button
                            label="Write review"
                            onClick={async () => {
                              await checkForReviewToMovie();
                            }}
                          />
                        </Card>

                        <DataTable
                          style={{ marginTop: "1%" }}
                          header={<h3>All reviews for selected movie</h3>}
                          value={selected_movie.reviews}
                          stripedRows
                          tableStyle={{ minWidth: "50rem" }}
                        >
                          <Column field="reviewText" header="Review "><Chip label="Action" /></Column>
                        </DataTable>

                        <DataTable
                          style={{ marginTop: "1%" }}
                          header={<h3>All Awards for selected movie</h3>}
                          value={selected_movie.awards}
                          stripedRows
                          tableStyle={{ minWidth: "50rem" }}
                        >
                          <Column field="award_name" header="Awards "></Column>
                        </DataTable>
                      </div>
                    )}
                  </div>
                </div>
              </>
            )}
            {activeIndex === 1 &&
              (reportMovie ? (
                <Card
                  title={reportMovie.title}
                  subTitle="Horror Movies with Most Reviews and Awards in year 2007"
                >
                  <h3>Id : {reportMovie.movieId}</h3>
                  <br />
                  <h3>Year : {reportMovie.releaseDate}</h3>
                  <br />
                  <h3>Genre : {reportMovie.genre}</h3>
                  <DataTable
                    style={{ marginTop: "1%" }}
                    header={<h3>All Reviews for selected movie</h3>}
                    value={reportMovie.reviews}
                    stripedRows
                    tableStyle={{ minWidth: "50rem" }}
                  >
                    <Column field="review_id" header="Review id "></Column>
                    <Column field="reviewText" header="Review "></Column>
                    <Column field="dateCreated" header="Date "></Column>
                  </DataTable>

                  <DataTable
                    style={{ marginTop: "1%" }}
                    header={<h3>All Awards for selected movie</h3>}
                    value={reportMovie.awards}
                    stripedRows
                    tableStyle={{ minWidth: "50rem" }}
                  >
                    <Column field="award_id" header="Award id "></Column>
                    <Column field="award_name" header="Award "></Column>
                    <Column field="dateCreated" header="Date "></Column>
                  </DataTable>
                </Card>
              ) : (
                <Card
                  title="Looks like there is no such 'Horror Movies with Most Reviews and Awards in year 2007'"
                  subTitle="Try to generate more data!"
                />
              ))}
          </div>
        </div>
      </div>
    </div>
  );
}
