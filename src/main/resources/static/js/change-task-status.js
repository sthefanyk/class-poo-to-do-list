document.addEventListener('DOMContentLoaded', () => {
    const searchTask = document.getElementById('searchTask');
    const statusFilter = document.getElementById('statusFilter');

    searchTask.addEventListener('input', function() {
        const filteredByName = searchByName(this);
        filterByStatus(statusFilter, filteredByName);
    });

    statusFilter.addEventListener('change', function() {
        const filteredByName = searchByName(searchTask);
        filterByStatus(this, filteredByName);
    });

    const searchByName = (searchTask) => {
        const filter = searchTask.value.toLowerCase();
        const tasks = document.querySelectorAll('.task');

        const filteredTasks = [];

        tasks.forEach(task => {
            const taskName = task.querySelector('p').textContent.toLowerCase();
            const matchesName = taskName.includes(filter);

            if (matchesName) {
                task.style.display = 'flex';
                filteredTasks.push(task);
            } else {
                task.style.display = 'none';
            }
        });

        return filteredTasks;
    }

    const filterByStatus = (status, tasks) => {
        const tasksToFilter = tasks ?? document.querySelectorAll('.task');
        const statusFilter = status.value;

        let foundVisible = false;

        tasksToFilter.forEach(task => {
            const taskStatus = task.querySelector('#tag').textContent;
            const matchesStatus = statusFilter === 'All' || taskStatus === statusFilter;

            if (matchesStatus) {
                task.style.display = 'flex';
                foundVisible = true;
            } else {
                task.style.display = 'none';
            }
        });

        const noTasksMessageFound = document.getElementById('noTasksMessageFound');
        noTasksMessageFound.classList.toggle('hidden', foundVisible);
    };
});
