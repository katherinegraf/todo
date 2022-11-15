import React, { useEffect, useState } from 'react';
import ExistingTasks from './ExistingTasks';
import TaskCreate from './TaskCreate';
import { Tachyons } from 'tachyons';
import { FETCH_URL, COMPLETE_URL, REACTIVATE_URL, DELETE_URL, STATUS_ACTIVE } from './settings';

const TaskManager = () => {

    const [existingTasks, setExistingTasks] = useState(null);
    const [allTasks, setAllTasks] = useState(null);
    const [activeTasks, setActiveTasks] = useState(null);
    const [loading, setLoading] = useState(null);
    const [error, setError] = useState(false);
    const [isActiveFilterEnabled, setIsActiveFilterEnabled] = useState(false);

    useEffect( () => {
        fetchData();
    }, [])

    async function fetchData() {
        setLoading(true);
        let req = await fetch(FETCH_URL);
        if (req.status < 400) {
            let jsonData = await req.json();
            setAllTasks(jsonData);
            let activeTaskList = jsonData.filter(task => task.status === STATUS_ACTIVE);
            setActiveTasks(activeTaskList);
            console.log(isActiveFilterEnabled);
            // setExistingTasks(isActiveFilterEnabled ? activeTasks : allTasks);
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

    function handleViewToggleButtonClick() {
        console.log(isActiveFilterEnabled);
        setIsActiveFilterEnabled(!isActiveFilterEnabled);
        console.log(isActiveFilterEnabled);
        setExistingTasks(isActiveFilterEnabled ? activeTasks : allTasks)
    }

    return (
        <div className='todoapp' >
            <header className='header'>
                To Do
            </header>
            {/* <button className='view-toggler'
                onClick = {() => {
                    handleViewToggleButtonClick();
                    // console.log(isActiveFilterEnabled);
                    // setIsActiveFilterEnabled(!isActiveFilterEnabled);
                    // console.log(isActiveFilterEnabled);
                    // setExistingTasks(isActiveFilterEnabled ? activeTasks : allTasks);
                }}
                >
                {(isActiveFilterEnabled ? 'Show Completed' : 'Hide Completed')}
            </button> */}
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
