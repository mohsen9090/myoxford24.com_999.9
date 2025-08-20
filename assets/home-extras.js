(function(){
  const mountCheats = ()=> {
    const lessons = document.querySelector('#lessons');
    if(!lessons) return;
    fetch('/fragments/cheats.html')
      .then(r=>r.text())
      .then(html=>lessons.insertAdjacentHTML('afterend', html))
      .catch(()=>{/*noop*/});
  };
  if(document.readyState === 'loading'){
    document.addEventListener('DOMContentLoaded', mountCheats);
  }else{
    mountCheats();
  }
})();
