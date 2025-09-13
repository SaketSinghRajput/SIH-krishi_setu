from django.shortcuts import render

# Common pages
def home(request):
    return render(request, 'common/Home/index.html')

def about_us(request):
    return render(request, 'common/About_us/index.html')

def contact(request):
    return render(request, 'common/Contact page/contact.html')

def faq(request):
    return render(request, 'common/FAQ/faq.html')

def login(request):
    return render(request, 'common/login/index.html')

def terms(request):
    return render(request, 'common/Terms/privacy.html')

# Buyer portal pages
def track(request):
    return render(request, 'buyer/Track/track.html')
