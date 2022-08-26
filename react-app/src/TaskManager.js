import React, { useEffect, useState } from 'react';
import ExistingTasks from './ExistingTasks';
import TaskCreate from './TaskCreate';
import { Tachyons } from 'tachyons';
import { FETCH_URL, COMPLETE_URL, REACTIVATE_URL, DELETE_URL } from './settings';

const TaskManager = () => {

    const [existingTasks, setExistingTasks] = useState(null);
    const [loading, setLoading] = useState(null);
    const [error, setError] = useState(false);

    // TODO change from static id to variable once there's login functionality
    const testUserId = 1
    const fetchUrl = FETCH_URL + testUserId;

    useEffect( () => {
        fetchData();
    }, [])

    async function fetchData() {
        setLoading(true);
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

    async function deleteTask(id) {
        setLoading(true);
        let url = DELETE_URL + id
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

    async function completeTask(id) {
        setLoading(true);
        let url = COMPLETE_URL + id
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

    async function reactivateTask(id) {
        setLoading(true);
        let url = REACTIVATE_URL + id
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
                reactivateTask = { reactivateTask }
            />
        </div> 
    )
}

export default TaskManager;
