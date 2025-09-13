from twilio.rest import Client
import os
import random

TWILIO_ACCOUNT_SID = os.getenv('TWILIO_ACCOUNT_SID', 'your_account_sid')
TWILIO_AUTH_TOKEN = os.getenv('TWILIO_AUTH_TOKEN', 'your_auth_token')
TWILIO_PHONE_NUMBER = os.getenv('TWILIO_PHONE_NUMBER', '+1234567890')

client = Client(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN)

# In-memory store for OTPs (use Redis or DB in production)
OTP_STORE = {}

def send_sms_otp(phone):
    otp = str(random.randint(100000, 999999))
    OTP_STORE[phone] = otp
    message = f"Your OTP is {otp}"
    client.messages.create(
        body=message,
        from_=TWILIO_PHONE_NUMBER,
        to=phone
    )
    return otp

def verify_sms_otp(phone, otp):
    return OTP_STORE.get(phone) == otp
