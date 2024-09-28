document.addEventListener('DOMContentLoaded', () => {
    const openModalBtn = document.getElementById('openModalBtn');
    const closeModalBtn = document.getElementById('closeModalBtn');
    const modal = document.getElementById('modal');
    const overlay = document.getElementById('overlay');
    const modalTitle = document.querySelector('#modal h2');
    const taskForm = document.getElementById('taskForm');

    // Abrir modal para criar nova tarefa
    openModalBtn.addEventListener('click', () => {
        document.getElementById("nameNewTask").value = "";
        document.getElementById("statusNewTask").selectedIndex = 0;
        
        modalTitle.textContent = "New Task";
        taskForm.setAttribute('action', `/task`);
        taskForm.setAttribute('method', 'POST');

        modal.classList.remove('hidden');
        overlay.classList.remove('hidden');
    });

    // Fechar modal
    closeModalBtn.addEventListener('click', () => {
        document.getElementById("nameNewTask").value = "";
        document.getElementById("statusNewTask").selectedIndex = 0;

        modal.classList.add('hidden');
        overlay.classList.add('hidden');
    });

    overlay.addEventListener('click', () => {
        modal.classList.add('hidden');
        overlay.classList.add('hidden');
    });

    const noTasksMsg = () => {
        const noTasksMessage = document.getElementById('noTasksMessage');
        if (document.querySelectorAll('.task').length === 0) {
            noTasksMessage.classList.remove('hidden');
        } else {
            noTasksMessage.classList.add('hidden');
        }
    }

    noTasksMsg();
});
