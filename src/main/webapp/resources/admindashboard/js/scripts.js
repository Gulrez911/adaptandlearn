$('a.nav-bars, a.close-menu').on('click', function () {
    $(this).closest('.container-fluid').find('nav').toggleClass('view');
});

$('a.more-menu').on('click', function () {
    var $this = $(this);
    $this.closest('nav').find('.sub-nav').toggleClass('view');
    if ($this.find('i.fa').hasClass('fa-angle-down')) {
        $this.find('i.fa').removeClass('fa-angle-down').addClass('fa-angle-up');
    } else {
        $this.find('i.fa').removeClass('fa-angle-up').addClass('fa-angle-down');
    }
});
$('button.btn-outline-secondary').on('click', function () {
    $(this).closest('.filter-block').toggleClass('view');
});
$('.view-style').on('click', function () {
    var $this = $(this),
        $icon = $this.find('i.fa'),
        $display = $('#gridListView');

    if ($icon.hasClass('fa-th')) {
        $icon.addClass('fa-list-ul').removeClass('fa-th');
        $display.addClass('grid-view').removeClass('list-view');
    } else {
        $icon.removeClass('fa-list-ul').addClass('fa-th');
        $display.removeClass('grid-view').addClass('list-view');
    }
});
$('.filter-block .filter h3').on('click', function () {
    var $this = $(this), icon = $this.find('i.fa'), list = $this.closest('.filter').find('ul');
    if (icon.hasClass('fa-plus')) {
        icon.removeClass('fa-plus').addClass('fa-minus');
        list.addClass('open');
    } else {
        icon.addClass('fa-plus').removeClass('fa-minus');
        list.removeClass('open');
    }
});
$('#passRange').on('change', function () {
    var $this = $(this), val = $this.val(), $display = $this.closest('.percentage').find('.rangeValue');
    $display.html(val).attr('style', `left: calc(${val}% - 20px);`);
});

$('.admin-actions .btn-icon').on('click', function () {
    var menu = $(this).closest('.admin-actions').find('ul');
    if (menu.hasClass('open')) {
        menu.removeClass('open');
    } else {
        menu.addClass('open');
    }
})








// Create Test
var steps = 1;

$('.btn.next').on('click', function () {
    if (steps === 1) {
        $('#step-1').addClass('d-none');
        $('#step-2').removeClass('d-none');
        $('.btn.back').removeClass('d-none');
        $('ul.form-steps li').eq(0).addClass('completed').removeClass('active');
        $('ul.form-steps li').eq(1).addClass('active');
    } else if (steps === 2) {
        $('#step-2').addClass('d-none');
        $('#step-3').removeClass('d-none');
        $('ul.form-steps li').eq(1).addClass('completed').removeClass('active');
        $('ul.form-steps li').eq(2).addClass('active');
    } else if (steps === 3) {
        $('#step-3').addClass('d-none');
        $('#step-4').removeClass('d-none');
        $('.btn.next').addClass('d-none');
        $('.btn.send-invitation').removeClass('d-none');
        $('ul.form-steps li').eq(2).addClass('completed').removeClass('active');
        $('ul.form-steps li').eq(3).addClass('active');
    }
    steps = steps + 1;
});
$('.btn.back').on('click', function () {
    if (steps === 2) {
        $('#step-1').removeClass('d-none');
        $('#step-2').addClass('d-none');
        $('.btn.back').addClass('d-none');
        $('ul.form-steps li').eq(0).addClass('active');
        $('ul.form-steps li').eq(1).removeClass('active');
    } else if (steps === 3) {
        $('#step-2').removeClass('d-none');
        $('#step-3').addClass('d-none');
        $('ul.form-steps li').eq(1).addClass('active');
        $('ul.form-steps li').eq(2).removeClass('active');
    } else if (steps === 4) {
        $('#step-3').removeClass('d-none');
        $('#step-4').addClass('d-none');
        $('.btn.next').removeClass('d-none');
        $('.btn.send-invitation').addClass('d-none');
        $('ul.form-steps li').eq(2).addClass('active');
        $('ul.form-steps li').eq(3).removeClass('active');
    }
    steps = steps - 1;
});

function createQuestion() {
    var val = $('#questionLevel').val();
    if (val === "1") {
        $('.mcq-type, .coding-type, .fullstack-type, .fillblanks-type, .match-type').addClass('d-none');
        $('.mcq-type').removeClass('d-none');
    } else if (val == "2") {
        $('.mcq-type, .coding-type, .fullstack-type, .fillblanks-type, .match-type').addClass('d-none');
        $('.coding-type').removeClass('d-none');
    } else if (val == "3" || val == "4") {
        $('.mcq-type, .coding-type, .fullstack-type, .fillblanks-type, .match-type').addClass('d-none');
        $('.fullstack-type').removeClass('d-none');
    } else if (val == "5") {
        $('.mcq-type, .coding-type, .fullstack-type, .fillblanks-type, .match-type').addClass('d-none');
        $('.fillblanks-type').removeClass('d-none');
    } else if (val == "6") {
        $('.mcq-type, .coding-type, .fullstack-type, .fillblanks-type, .match-type').addClass('d-none');
        $('.match-type').removeClass('d-none');
    } else {
        $('.mcq-type, .coding-type, .fullstack-type, .fillblanks-type, .match-type').addClass('d-none');
    }
}
createQuestion();

$('#questionLevel').on('change', function () {
    createQuestion();
});