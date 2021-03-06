import React, { useEffect, useState } from 'react';
import ExistingTasks from './ExistingTasks';
import TaskCreate from './TaskCreate';

const TaskManager = () => {

    const [existingTasks, setExistingTasks] = useState(null);
    const [loading, setLoading] = useState(null);
    const [error, setError] = useState(false);

    // TODO change from static id to variable once there's login functionality
    const testUserId = 1
    const fetchUrl = "http://localhost:2000/allTasksForUser/" + testUserId;

    // NOTE Other methods are triggered by events; fetchData isn't and so requires useEffect to initialize the call.
    // This is for loading the page with data before the user interacts with it.
    useEffect( () => {
        fetchData();
    }, [])

    async function fetchData() {
        setLoading(true);
        // TODO once you can toggle between task types, use state to choose url?
            // seems a choice b/t fetch all and then filter, or fetch based on filter
        let req = await fetch(fetchUrl);
        if (req.status < 400) {
            let jsonData = await req.json();
            // WIP/NOTES: below is idea from A on toggling task categories 
                // iterate through map of tasks and add to new lists based on status
                // let allTasks = jsonData;
                // let completedTasks = jsonData.map((task) => 
                //     if (task.status == 'complete')
                //         return task
                // )
            setExistingTasks(jsonData);
        } else {
            setError(true);
        }
        setLoading(false);
    }

    const deleteUrl = "//localhost:2000/deleteTask/"

    async function deleteTask(id) {
        setLoading(true);
        let url = deleteUrl + id
        let req = await fetch(url, {
            method: 'DELETE',
        });
        if (req.status < 400) {
            fetchData();
        } else {
            setError(true);
        }
        setLoading(false);
    }

    const completeUrl = "//localhost:2000/completeTask/"

    async function completeTask(id) {
        setLoading(true);
        let url = completeUrl + id
        let req = await fetch(url, {
            method: 'PATCH',
        });
        if (req.status < 400) {
            fetchData();
        } else {
            setError(true);
        }
        setLoading(false);
    }

    return (
        <div className='todoapp w-100' >
            <header className='header'>
                To Do
            </header>
            <TaskCreate 
                fetchData = { fetchData }
            />
            <ExistingTasks
                existingTasks = { existingTasks }
                deleteTask = { deleteTask }
                completeTask = { completeTask }
            />
        </div> 
    )
}

export default TaskManager;
