import React from 'react';
import ExistingTasks, { tasks } from './ExistingTasks'

export default Task => {

    const markComplete = (id) => {
        
        tasks.forEach((task) => {
            if (task.id == id) {
                task.isCompleted = true;
            }
        });
        return tasks
    }

    return null

}