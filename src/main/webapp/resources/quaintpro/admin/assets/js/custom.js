// ==============MENU CODE START=============
window.addEventListener("resize", function() {
  "use strict"; window.location.reload(); 
});
document.addEventListener("DOMContentLoaded", function()
{
/////// Prevent closing from click inside dropdown
document.querySelectorAll('.dropdown-menu').forEach(function(element)
{
       element.addEventListener('click', function(e)
       {
               e.stopPropagation();
       });
})
// make it as accordion for smaller screens
if (window.innerWidth < 992)
{
       // close all inner dropdowns when parent is closed
       document.querySelectorAll('.navbar .dropdown').forEach(function(everydropdown)
       {
               everydropdown.addEventListener('hidden.bs.dropdown', function()
               {
                       // after dropdown is hidden, then find all submenus
                       this.querySelectorAll('.submenu')
                               .forEach(function(everysubmenu)
                               {
                                       // hide every submenu as well
                                       everysubmenu
                                               .style
                                               .display =
                                               'none';
                               });
               })
       });
       document.querySelectorAll('.dropdown-menu a').forEach(function(element)
       {
               element.addEventListener('click', function(e)
               {
                       let nextEl = this.nextElementSibling;
                       if (nextEl && nextEl.classList
                               .contains('submenu'))
                       {
                               // prevent opening link if link needs to open dropdown
                               e.preventDefault();
                               console.log(nextEl);
                               if (nextEl.style.display ==
                                       'block')
                               {
                                       nextEl.style.display =
                                               'none';
                               }
                               else
                               {
                                       nextEl.style.display =
                                               'block';
                               }
                       }
               });
       })
}
// end if innerWidth
});
// ==============MENU CODE END=============
// ==============Question Category select code start============
$(document).ready(function(){
        $('ul#navi li a').click(function(){
          $('li a').removeClass("active");
          $(this).addClass("active");
      });
      });
// ==============Question Category select code End============
// ==============Range code start============
$(function () {
        $('.preview4').createSlide({ progress: true, interval: 5 });
});
// ==============Range code END===========


// ===========Score Distribution graph code start===========
var options = {
        series: [{
        name: 'Net Profit',
        data: [44, 55, 57, 56, 61, 58, 63, 60, 66, 50, 89]
      }],
        chart: {
        type: 'bar',
        height: 350
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: '55%',
          endingShape: 'rounded'
        },
      },
      dataLabels: {
        enabled: false
      },
      colors: ['#4782BC',],
      stroke: {
        show: true,
        width: 2,
        colors: ['transparent']
      },
      xaxis: {
        categories: ['1-9', '10-19', '20-29', '30-39', '40-49', '50-59', '60-69', '70-79', '80-89', '90-99', '100'],
      },
      yaxis: {
        title: {
          text: 'No. of Candidates'
        }
      },
     
      fill: {
        opacity: 1
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return "$ " + val + " thousands"
          }
        }
      }
      };

      var chart = new ApexCharts(document.querySelector("#chart"), options);
      chart.render();
// ===========Score Distribution graph code End===========


// ==========Toll Tip Code Start=============
// var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
// var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
//   return new bootstrap.Tooltip(tooltipTriggerEl)
// })
// var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip_new"]'))
// var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
//   return new bootstrap.Tooltip(tooltipTriggerEl)
// })

// ==========Toll Tip Code End=============



var options = {
  series: [44, 55, 41, 17, 15],
  chart: {
  type: 'donut',
},
responsive: [{
  breakpoint: 480,
  options: {
    chart: {
      width: 200
    },
    legend: {
      position: 'bottom'
    }
  }
}]
};

var chart = new ApexCharts(document.querySelector("#Result"), options);
chart.render();



